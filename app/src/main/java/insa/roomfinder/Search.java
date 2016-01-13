package insa.roomfinder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

import insa.roomfinder.requests.Request;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;

/**
 * Created by pierre on 02/01/16.
 */


public class Search extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    private EditText mDateText;
    private Button mSearchButton;
    private NetworkInterface mNi;
    private Spinner mSiteSpinner;
    private ArrayList<String> mSitesName;

    public static Search newInstance(int sectionNumber) {
        Search fragment = new Search();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Search() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mNi = new Retrofit.Builder().baseUrl(NetworkInterface.ENDPOINT).addConverterFactory(SimpleXmlConverterFactory.create()).build().create(NetworkInterface.class);

        mSearchButton = (Button) getView().findViewById(R.id.button);
        mDateText = (EditText) getView().findViewById(R.id.date);
        mSiteSpinner = (Spinner) getView().findViewById(R.id.spinner);
        mSitesName = Data.getInstance().getSitesName();


        Calendar mcurrentDate = Calendar.getInstance();
        int year = mcurrentDate.get(Calendar.YEAR);
        int month = mcurrentDate.get(Calendar.MONTH) + 1; //Cause months start at 0
        int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        String sYear = String.valueOf(year);
        String sMonth = MonthsUtil.monthToString(month, getActivity().getApplicationContext());
        String sDay = String.valueOf(day);
        if (day < 10)
            sDay="0"+sDay;

        mDateText.setText(sDay + " " + sMonth + " " + sYear);
        mDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                final int mMonth = mcurrentDate.get(Calendar.MONTH) + 1;//Cause months start at 0
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        // TODO Auto-generated method stub
                        String year = String.valueOf(selectedYear);
                        String month = MonthsUtil.monthToString(selectedMonth + 1, getActivity().getApplicationContext());
                        String day = String.valueOf(selectedDay);
                        if (selectedDay < 10)
                            day = "0" + day;
                        mDateText.setText(day + " " + month + " " + year);
                    }
                }, mYear, mMonth - 1, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNi.sendXMLRequest(new Request()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Response<Void> response, Retrofit retrofit) {
                        // Il faudra changer la liste des salles a afficher
                        // Faire de Liste Salles un fragment, pour tester tu peux afficher la liste de salle apres le login
                        // Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        // startActivity(intent);
                        System.out.println("SUCCESS -> " + response.code());
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("ERROR : " + t);
                    }
                });
            }
        });

        SharedPreferences sharedPreferences = getView().getContext().getSharedPreferences("Profile", Context.MODE_PRIVATE);
        String site = sharedPreferences.getString("site","");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, mSitesName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSiteSpinner.setAdapter(adapter);
        if (!mSiteSpinner.equals(null)) {
            int spinnerPosition = adapter.getPosition(site);
            mSiteSpinner.setSelection(spinnerPosition);
        }


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
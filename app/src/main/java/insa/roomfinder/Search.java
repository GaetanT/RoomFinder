package insa.roomfinder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.IOException;
import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
    private Ni mNi;

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
        View rootView = inflater.inflate(R.layout.search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NetworkInterface networkInterface = new RestAdapter.Builder().setEndpoint(NetworkInterface.ENDPOINT).build().create(NetworkInterface.class);

        mSearchButton = (Button) getView().findViewById(R.id.button);
        mDateText = (EditText) getView().findViewById(R.id.date);
        Calendar mcurrentDate = Calendar.getInstance();
        int year = mcurrentDate.get(Calendar.YEAR);
        int month = mcurrentDate.get(Calendar.MONTH) + 1; //Cause months start at 0
        int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        String sYear = String.valueOf(year);
        String sMonth = MonthsUtil.monthToString(month, getActivity().getApplicationContext());
        String sDay = String.valueOf(day);
        if (day < 10)
            sDay="0"+sDay;


        mDateText.setText(sDay+" "+sMonth+" "+sYear);
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
                String xml = "<request></request>";
                mNi.sendXMLRequest(xml, new Callback<Void>() {
                    @Override
                    public void success(Void aVoid, Response response) {
                        System.out.println("Success : " + response);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        System.out.println("Error : " + error);
                    }
                });
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
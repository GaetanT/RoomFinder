package insa.roomfinder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import insa.roomfinder.data.Data;
import insa.roomfinder.data.Equipment;
import insa.roomfinder.data.Equipments;
import insa.roomfinder.data.ExtendedRooms;
import insa.roomfinder.data.Rooms;
import insa.roomfinder.requests.SearchRequest;
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
    private Spinner mHourSpinner;
    private Spinner mLengthSpinner;
    private ArrayList<String> mSitesName;
    private ArrayList<String> mRoomsName;
    private ArrayList<String> mEquipmentsName;
    private LinearLayout mLayoutEquipment1;
    private LinearLayout mLayoutEquipment2;
    private EditText mSize;
    private ArrayList<CheckBox> mCheckboxes;

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
        mHourSpinner = (Spinner) getView().findViewById(R.id.hourSpinner);
        mLengthSpinner = (Spinner) getView().findViewById(R.id.lengthSpinner);
        mSize = (EditText) getView().findViewById(R.id.searchSize);
        mSitesName = Data.getInstance().getSitesName();
        mRoomsName = Data.getInstance().getExtendedRoomsName();
        mEquipmentsName = Data.getInstance().getEquipmentsName();
        mLayoutEquipment1 = (LinearLayout) getView().findViewById(R.id.equipmentView1);
        mLayoutEquipment2 = (LinearLayout) getView().findViewById(R.id.equipmentView2);
        mCheckboxes = new ArrayList<>();


        //Ajout des choix d'équipements
        int i;
        for (i=0; i<mEquipmentsName.size(); i++) {
            CheckBox checkbox = new CheckBox(getView().getContext());
            checkbox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            checkbox.setText(mEquipmentsName.get(i));
            mCheckboxes.add(i,checkbox);
            if (i % 2 == 0) {
                mLayoutEquipment1.addView(checkbox);
            } else {
                mLayoutEquipment2.addView(checkbox);
            }


        }


        Calendar mcurrentDate = Calendar.getInstance();
        int year = mcurrentDate.get(Calendar.YEAR);
        int month = mcurrentDate.get(Calendar.MONTH) + 1; //Cause months start at 0
        int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        String sYear = String.valueOf(year);
        String sMonth = DateUtil.monthToString(month, getActivity().getApplicationContext());
        String sDay = String.valueOf(day);
        if (day < 10)
            sDay="0"+sDay;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.item_list_row, mRoomsName);
        AutoCompleteTextView textView = (AutoCompleteTextView) getView().findViewById(R.id.searchView);
        textView.setAdapter(adapter);

       // Utiliser Data.getInstance().getExtendedRoomsName()) à la place de COUNTRIES dans le paragraphe ci dessus

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
                        String month = DateUtil.monthToString(selectedMonth + 1, getActivity().getApplicationContext());
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


        SharedPreferences sharedPreferences = getView().getContext().getSharedPreferences("Profile", Context.MODE_PRIVATE);
        String site = sharedPreferences.getString("site","");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, mSitesName);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSiteSpinner.setAdapter(adapter2);
        if (!mSiteSpinner.equals(null)) {
            int spinnerPosition = adapter2.getPosition(site);
            mSiteSpinner.setSelection(spinnerPosition);
        }


        //Lance la recherche
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String site = mSiteSpinner.getSelectedItem().toString();
                String date = DateUtil.dateToYMD(mDateText.getText().toString(), getView().getContext());
                Integer startSlot = mHourSpinner.getSelectedItemPosition() + 1;
                Integer endSlot = startSlot + mLengthSpinner.getSelectedItemPosition() + 1;
                String sizeString = mSize.getText().toString();
                        if (sizeString.isEmpty() || sizeString == null)
                            sizeString = "5";
                Integer size = Integer.parseInt(sizeString);

                Equipments equipments = new Equipments();
                int i;
                for (i=0; i<mEquipmentsName.size(); i++) {
                    if (mCheckboxes.get(i).isChecked())
                       equipments.addEquipment(new Equipment(mCheckboxes.get(i).getText().toString()));
                }

                SearchRequest searchRequest = new SearchRequest(null,equipments,startSlot,endSlot,size,site,date);
                mNi.searchRooms(searchRequest).enqueue(new Callback<Rooms>() {
                    @Override
                    public void onResponse(Response<Rooms> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            ExtendedRooms extendedRooms = ExtendedRooms.roomsToExtendedRooms(response.body());

                            if (extendedRooms != null && extendedRooms.getExtendedRooms().size() != 0) {
                                Intent intent = new Intent(getActivity(), ResultActivity.class);
                                intent.putExtra("extendedRooms", extendedRooms);
                                startActivity(intent);
                            }
                            else {
                                Toast toast = Toast.makeText(getView().getContext(), "No room found", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, -500);
                                toast.show();
                            }
                        } else {
                            System.out.println("Error : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast toast = Toast.makeText(getView().getContext(), "A problem occurred", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, -500);
                        toast.show();
                        t.printStackTrace();

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
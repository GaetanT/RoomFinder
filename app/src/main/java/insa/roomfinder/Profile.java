package insa.roomfinder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import insa.roomfinder.data.Data;

/**
 * Created by pierre on 02/01/16.
 */
public class Profile extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    EditText mMailEditText;
    EditText mFullNameEditText;
    EditText mPhoneNumberEditText;
    Button mProfileButton;
    Spinner mSiteSpinner;
    Spinner mFavoriteRoomSpinner;
    ArrayList<String> mRoomsName;
    ArrayList<String> mSitesName;

    public static Profile newInstance(int sectionNumber) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Profile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFullNameEditText = (EditText) getView().findViewById(R.id.fullNameEditText);
        mMailEditText = (EditText) getView().findViewById(R.id.mailProfile);
        mPhoneNumberEditText = (EditText) getView().findViewById(R.id.phoneNumberEditText);
        mSiteSpinner = (Spinner) getView().findViewById(R.id.siteSpinner);
        mFavoriteRoomSpinner = (Spinner) getView().findViewById(R.id.favoriteRoomSpinner);
        mProfileButton = (Button) getView().findViewById(R.id.profileButton);
        mRoomsName = Data.getInstance().getExtendedRoomsName();
        mSitesName = Data.getInstance().getSitesName();

        //in order to avoid the user to change his information that only the server is allow to modify
        mFullNameEditText.setKeyListener(null);
        mMailEditText.setKeyListener(null);
        mPhoneNumberEditText.setKeyListener(null);

        SharedPreferences sharedPreferences = getView().getContext().getSharedPreferences("Profile", Context.MODE_PRIVATE);
        String fullName = sharedPreferences.getString("name", "");
        String mail = sharedPreferences.getString("mail","");
        String phoneNumber = sharedPreferences.getString("phone","");
        String site = sharedPreferences.getString("site","");
        String favoriteRoom = sharedPreferences.getString("favoriteRoom","");
        mFullNameEditText.setText(fullName);
        mMailEditText.setText(mail);
        mPhoneNumberEditText.setText(phoneNumber);

        System.out.println("NAME : " + fullName);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, mSitesName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSiteSpinner.setAdapter(adapter);
        if (!mSiteSpinner.equals(null)) {
            int spinnerPosition = adapter.getPosition(site);
            mSiteSpinner.setSelection(spinnerPosition);
        }

        ArrayAdapter<String> adapterBis = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, mRoomsName);
        adapterBis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFavoriteRoomSpinner.setAdapter(adapterBis);
        if (!mFavoriteRoomSpinner.equals(null)) {
            int spinnerPositionBis = adapterBis.getPosition(favoriteRoom);
            mFavoriteRoomSpinner.setSelection(spinnerPositionBis);
        }


        mProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = mFullNameEditText.getText().toString();
                String mail =  mMailEditText.getText().toString();
                String phoneNumber = mPhoneNumberEditText.getText().toString();
                String site = mSiteSpinner.getSelectedItem().toString();
                String favoriteRoom = mFavoriteRoomSpinner.getSelectedItem().toString();

                SharedPreferences sharedPreferences = getView().getContext().getSharedPreferences("Profile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("fullName", fullName);
                editor.putString("mail", mail);
                editor.putString("phoneNumber", phoneNumber);
                editor.putString("site", site);
                editor.putString("favoriteRoom", favoriteRoom);
                editor.apply();

                Toast toast = Toast.makeText(getView().getContext(), "New profile saved", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, -500);
                toast.show();
            }
        });


    }
}

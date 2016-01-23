package insa.roomfinder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import insa.roomfinder.data.Data;
import insa.roomfinder.data.Equipments;
import insa.roomfinder.data.ExtendedRooms;
import insa.roomfinder.data.Sites;
import insa.roomfinder.requests.ConnectionRequest;
import insa.roomfinder.responses.ConnectionResponse;
import insa.roomfinder.responses.ProfileResponse;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.SimpleXmlConverterFactory;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;
    private Boolean mUserAlreadyConnected;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mUserAlreadyConnected = false;
        mSharedPreferences = getApplicationContext().getSharedPreferences("Profile", Context.MODE_PRIVATE);

        // Set up the login form.
        getLoaderManager().initLoader(0, null, this);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.password || id == EditorInfo.IME_NULL) {

                    //In order to quickly hide the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //In order to quickly hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                attemptLogin();
            }
        });

        String storedMail = mSharedPreferences.getString("mail", "");
        mEmailView.setText(storedMail);
        String  storedPassword = mSharedPreferences.getString("password","");
        if (!storedMail.isEmpty() && !storedPassword.isEmpty()) {
            mUserAlreadyConnected = true;
            attemptLogin();
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        String email="";
        String password="";

        if(mUserAlreadyConnected) {
            mUserAlreadyConnected = false;
            email = mSharedPreferences.getString("mail", "");
            password = mSharedPreferences.getString("password", "");
        } else {
            // Reset errors.
            mEmailView.setError(null);
            mPasswordView.setError(null);

            // Store values at the time of the login attempt.
            email = mEmailView.getText().toString();
            String tempPassword = mPasswordView.getText().toString();
            password = new String(Hex.encodeHex(DigestUtils.sha(tempPassword))); //Weird syntax because of an intern android studio problem
        }

        if (TextUtils.isEmpty(email)) { // Check if the mail address is empty
            mEmailView.setError(getString(R.string.error_empty_email));
            mEmailView.requestFocus();

        } else if (!isEmailValid(email)) { //Check if the mail address has the correct shape
            mEmailView.setError(getString(R.string.error_invalid_email));
            mEmailView.requestFocus();

        } else if (TextUtils.isEmpty(password)) { //Check if the password is empty
            mPasswordView.setError(getString(R.string.error_empty_password));
            mPasswordView.requestFocus();

        } else if (!isPasswordValid(password)) { // Check for a valid password
            mPasswordView.setError(getString(R.string.error_invalid_password));
            mPasswordView.requestFocus();

        } else {
            // The information required were correctly given
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            // TODO: attempt authentication against a network service.
            showProgress(true);
            ConnectOperation connectOperation = new ConnectOperation(email,password);
            connectOperation.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {}

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private void storeID (String mail, String password) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("mail", mail);
        editor.putString("password", password);
        editor.apply();
    }

    private void storeProfile (String id, String name, String mail, String site, String room, String phone) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("id", id);
        editor.putString("mail", mail);
        editor.putString("name", name);
        editor.putString("site", site);
        editor.putString("favoriteRoom", room);
        editor.putString("phone", phone);
        editor.apply();
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class ConnectOperation extends AsyncTask<Void,Void,Integer> {

        private final String email;
        private final String password;
        private NetworkInterface mNi;
        private EditText mPasswordView = (EditText) findViewById(R.id.password);

        public ConnectOperation(String mail, String pwd) {
            email = mail;
            password = pwd;

            mNi = new Retrofit.Builder().baseUrl(NetworkInterface.ENDPOINT).addConverterFactory(SimpleXmlConverterFactory.create()).build().create(NetworkInterface.class);
        }

        @Override
        protected Integer doInBackground(Void... params) {
            Integer errorCode = 0;
            Integer employeeId = 0;

            try {
                Response<ConnectionResponse> connectionResponse = mNi.attemptConnection(new ConnectionRequest(email, password)).execute();
                if (connectionResponse.isSuccess() && connectionResponse.body().getmConnected()) {
                    storeID(email, password);
                    employeeId = connectionResponse.body().getmUserId();
                }
                else if (connectionResponse.isSuccess() && !connectionResponse.body().getmConnected()) {
                    errorCode = 1; //wrong password
                } else
                    errorCode = 4;

                if (errorCode==0) {
                    Response<ExtendedRooms> roomsResponse = mNi.getExtendedRooms().execute(); //Retrieve extended rooms
                    if (roomsResponse.isSuccess()) {
                        HashMap<Integer,Integer> idToIndexExtendedRooms = new HashMap<>();
                        int i;
                        int roomId;
                        for(i=0;i<roomsResponse.body().getExtendedRooms().size();i++) {
                            roomId = roomsResponse.body().getExtendedRooms().get(i).getRoom().getId();
                            idToIndexExtendedRooms.put(roomId, i);
                            System.out.println("roomId " + roomId +  " at index : " + idToIndexExtendedRooms.get(roomId));
                        }
                        //roomsResponse.body().setIdToIndexExtendedRooms(idToIndexExtendedRooms);
                        Data.getInstance().setExtendedRooms(roomsResponse.body());
                        Data.getInstance().setIdToIndexExtendedRooms(idToIndexExtendedRooms);

                    }
                    else
                        errorCode = 2; //data not retrieved
                }

                if (errorCode==0) {
                    Response<Sites> sitesResponse = mNi.getSites().execute(); //Retrieve sites
                    if (sitesResponse.isSuccess())
                        Data.getInstance().setSites(sitesResponse.body());
                    else
                        errorCode = 2; //data not retrieved
                }

                if (errorCode==0) {
                    Response<Equipments> equipmentsResponse = mNi.getEquipmentsList().execute(); //Retrieve the equipment list
                    if (equipmentsResponse.isSuccess())
                        Data.getInstance().setEquipmentsList(equipmentsResponse.body());
                    else
                        errorCode = 2; //data not retrieved
                }

                if (errorCode==0) {
                    Response<ProfileResponse> profileResponseResponse = mNi.getProfile(String.valueOf(employeeId)).execute(); //Retrieve user profile
                    if (profileResponseResponse.isSuccess()) {
                        ProfileResponse pr = profileResponseResponse.body();
                        storeProfile(pr.getId().toString(), pr.getName(), pr.getMail(), pr.getFavSite().getName(),pr.getFavRoom().getName(),pr.getPhone());
                    } else
                        errorCode = 3;
                }
            } catch (IOException e) {
                e.printStackTrace();
                errorCode = 4;
            }
            return errorCode;
        }

        @Override
        protected void onPostExecute(Integer errorCode) {
            super.onPostExecute(errorCode);

            showProgress(false);

            switch (errorCode) {
                case 0 : //Let's change to the mainActivity !
                    finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case 1 : //wrong password
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mPasswordView.requestFocus();
                    break;
                case 2 : //data not retrieved
                    Toast toast = Toast.makeText(getApplicationContext(), "Error : Data couldn't be retrieved", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
                    toast.show();
                    break;
                case 3 :
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Error : Profile user couldn't be retrieved", Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
                    toast1.show();
                    break;
                default:
                    Toast toast2 = Toast.makeText(getApplicationContext(), "Error : A network problem occurred", Toast.LENGTH_SHORT);
                    toast2.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
                    toast2.show();
                    break;
            }

        }
    }
}




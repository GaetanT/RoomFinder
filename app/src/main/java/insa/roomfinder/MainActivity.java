package insa.roomfinder;

import android.app.DatePickerDialog;
import android.sax.TextElementListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private EditText mDateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction().replace(R.id.container, Favorite.newInstance(position + 1)).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.container, Search.newInstance(position + 1)).commit();
                getSupportFragmentManager().executePendingTransactions(); //Pour etre sur que la transaction est bien terminée, sinon ca bug
                mDateText = (EditText) findViewById(R.id.date);
                Calendar mcurrentDate = Calendar.getInstance();
                int year = mcurrentDate.get(Calendar.YEAR);
                int month = mcurrentDate.get(Calendar.MONTH) + 1; //Cause months start at 0
                int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                String sYear = String.valueOf(year);
                String sMonth = MonthsUtil.monthToString(month);
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

                        DatePickerDialog mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                                // TODO Auto-generated method stub
                                String year = String.valueOf(selectedYear);
                                String month = MonthsUtil.monthToString(mMonth);
                                String day = String.valueOf(selectedDay);
                                if (selectedDay < 10)
                                    day="0"+day;
                                mDateText.setText(day+" "+month+" "+year);
                            }
                        }, mYear, mMonth, mDay);
                        mDatePicker.setTitle("Select date");
                        mDatePicker.show();
                    }
                });
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.container, Profile.newInstance(position + 1)).commit();
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.container, Profile.newInstance(position + 1)).commit();
                break;
            case 4:
                fragmentManager.beginTransaction().replace(R.id.container, About.newInstance(position + 1)).commit();
                break;
            default:
                fragmentManager.beginTransaction().replace(R.id.container, Profile.newInstance(position + 1)).commit();
        }
        /*fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();}
        */
    }

    public void onSectionAttached(int number) {
        switch (number) {
            /*
            section1 ->Salles Favorites
            section2 ->Recherche de Salles
            section3 ->Profil
            section4 ->Deconnection
            section5 ->About
            */
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

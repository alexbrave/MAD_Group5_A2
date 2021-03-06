/*
*	PROJECT: PROG3150 - ASSIGNMENT 1
*	FILE: MainActivity.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2020/04/08
*	DESCRIPTION:
		This file contains MainActivity java class which is a subclass of AppCompatActivity. The purpose of this class is to create a home screen so that
		the user can fill out the necessary information and plan for a trip.

*/
package com.example.group5_a2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 *  NAME : MainActivity
 *  PURPOSE : The purpose of this class is to create a home screen so that the user can fill out the necessary information and plan for a trip.
 */
public class MainActivity extends AppCompatActivity {
    //View Objects
    private EditText mStartDate;
    private EditText mEndDate;
    private EditText mDestination;
    private SeekBar mNumAdultsSeekBar;
    private SeekBar mNumChildSeekBar;
    private TextView mNumOfAdultsProgress;
    private TextView mNumOfChildrenProgress;
    private DatePickerDialog mDatePickerDialog;
    private Button mSearchButton;

    //Model Object
    private MainActivityModel mainModel;

    // Getters of Intractable View Objects
    public Button getSearchButton() { return mSearchButton; }
    public EditText getStartDate() {
        return mStartDate;
    }
    public EditText getEndDate() {
        return mEndDate;
    }
    public EditText getDestination() {
        return mDestination;
    }
    public TextView getNumAdults() {
        return mNumOfAdultsProgress;
    }
    public TextView getNumChild() { return mNumOfChildrenProgress; }

    //Shared Preferences Object
    private SharedPreferences savedValues;

    //Static Booleans for Controller
    static public boolean mCanGoNextState = false;
    static public boolean mReset = false;
    private final int confirm_ticket_item = 2;
    private final String ACTIVITY_KEY = "ACTIVITY_KEY";
    private String ACTIVITY = "FIRST";

    /*
     *	Function: onCreate(Bundle savedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the main activity class. And create
     *          event handler for widgets on the activity_main layout
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the Model Object
        mainModel = new MainActivityModel();

        savedValues = getSharedPreferences(mainModel.getTAGSharedPrefs(), MODE_PRIVATE);

        mDestination = (EditText) findViewById(R.id.destination);
        mStartDate = (EditText) findViewById(R.id.startDate);
        mEndDate = (EditText) findViewById(R.id.endDate);
        mNumAdultsSeekBar = (SeekBar) findViewById(R.id.numOfAdults_seekBar);
        mNumChildSeekBar = (SeekBar) findViewById(R.id.numOfChildren_seekBar);
        mNumOfAdultsProgress = (TextView) findViewById(R.id.numOfAdults_progress);
        mNumOfChildrenProgress = (TextView) findViewById(R.id.numOfChildren_progress);
        mSearchButton = (Button) findViewById(R.id.searchHotels_btn);

        mNumAdultsSeekBar.setMax(10);
        mNumChildSeekBar.setMax(10);


        /* Check whether the state of the instance not empty.
         *  If not empty, restore the state
         */
        if (savedInstanceState != null) {
            // Destination
            mainModel.setDestinationValue(savedInstanceState.getString(mainModel.getDestinationKey()));
            mDestination.setText(mainModel.getDestinationValue());

            // Start date of the trip
            mainModel.setStartDateValue(savedInstanceState.getString(mainModel.getStartDateKey()));
            mStartDate.setText(mainModel.getStartDateValue());

            // End Date of the trip
            mainModel.setEndDateValue(savedInstanceState.getString(mainModel.getEndDateKey()));
            mEndDate.setText(mainModel.getEndDateValue());

            // Num of Adults
            mainModel.setNumAdultsValue(savedInstanceState.getString(mainModel.getNumAdultsKey()));
            mNumOfAdultsProgress.setText(mainModel.getNumAdultsValue());

            // Num of children
            mainModel.setNumChildrenValue(savedInstanceState.getString(mainModel.getNumChildrenKey()));
            mNumOfChildrenProgress.setText(mainModel.getNumChildrenValue());
        } else {
            mDestination.requestFocus();
        }

        // hide keyboard after clicking outside EditText
        mDestination.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        // Select the beginning date of the trip
        mStartDate.setInputType(InputType.TYPE_NULL);
        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(mStartDate);
            }
        });

        // Select the end date of the trip
        mEndDate.setInputType(InputType.TYPE_NULL);
        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(mEndDate);
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                // check whether all the inputs are filled
                if (checkInput()) {
                    mCanGoNextState = true;

                    // added to test/debug/implement state management - alex, feb 13
                    SaveData();

                    Intent intent = new Intent(MainActivity.this, ASyncDownload.class);
                    intent.putExtra(ACTIVITY_KEY, ACTIVITY);
                    startActivity(intent);
                }
                return;
            }
        });

        mNumAdultsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mNumOfAdultsProgress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mNumChildSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mNumOfChildrenProgress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // For testing purpose
        mDestination.setText("Waterloo");
        mStartDate.setText("2021/3/21");
        mEndDate.setText("2021/3/22");
        mNumAdultsSeekBar.setProgress(5);
        mNumChildSeekBar.setProgress(5);
    }

    /*
     *	Function: SaveData()
     *	Description:
     *       The purpose of this function is to test/debug/implement state management
     *	Parameter: None
     *	Return: void: Not return anything
     */
    private void SaveData() {
        // save the instance variables

        // Create variable to edit SharedPreferences
        SharedPreferences.Editor editor = savedValues.edit();
        // Store the destination
        EditText et1 = (EditText) findViewById(R.id.destination);
        editor.putString(mainModel.getTAGSharedDestination(), et1.getText().toString());
        //Store the start date
        EditText et3 = (EditText) findViewById(R.id.startDate);
        editor.putString(mainModel.getTAGSharedStartDate(), et3.getText().toString());
        // Store the end date
        EditText et4 = (EditText) findViewById(R.id.endDate);
        editor.putString(mainModel.getTAGSharedEndDate(), et4.getText().toString());
        // Store the number of adults
        TextView et2 = (TextView) findViewById(R.id.numOfAdults_progress);
        editor.putInt(mainModel.getTAGSharedNumAdults(), Integer.parseInt(et2.getText().toString()));
        // Store the number of children
        TextView et5 = (TextView) findViewById(R.id.numOfChildren_progress);
        editor.putInt(mainModel.getTAGSSharedNumChildren(), Integer.parseInt(et5.getText().toString()));

        editor.apply();
    }


    /*
     *	Function: onSaveInstanceState(Bundle savedInstanceState)
     *	Description:
     *       The purpose of this function is to save the state of the instance
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        mDestination = (EditText) findViewById(R.id.destination);
        mStartDate = (EditText) findViewById(R.id.startDate);
        mEndDate = (EditText) findViewById(R.id.endDate);
        mNumOfAdultsProgress = (TextView) findViewById(R.id.numOfAdults_progress);
        mNumOfChildrenProgress = (TextView) findViewById(R.id.numOfChildren_progress);

        if (mDestination.getText().toString().isEmpty() != true) {
            mainModel.setDestinationValue(mDestination.getText().toString());
            savedInstanceState.putString(mainModel.getDestinationKey(), mainModel.getDestinationValue());
        }


        if (mStartDate.getText().toString().isEmpty() != true) {
            mainModel.setStartDateValue(mStartDate.getText().toString());
            savedInstanceState.putString(mainModel.getStartDateKey(), mainModel.getStartDateValue());
        }

        if (mEndDate.getText().toString().isEmpty() != true) {
            mainModel.setEndDateValue(mEndDate.getText().toString());
            savedInstanceState.putString(mainModel.getEndDateKey(), mainModel.getEndDateValue());
        }

        if (mNumOfAdultsProgress.getText().toString().isEmpty() != true) {
            mainModel.setNumAdultsValue(mNumOfAdultsProgress.getText().toString());
            savedInstanceState.putString(mainModel.getNumAdultsKey(), mainModel.getNumAdultsValue());
        }

        if (mNumOfChildrenProgress.getText().toString().isEmpty() != true) {
            mainModel.setNumChildrenValue(mNumOfChildrenProgress.getText().toString());
            savedInstanceState.putString(mainModel.getNumChildrenKey(), mainModel.getNumChildrenValue());
        }

    }

    /*
     *	Function: onResume()
     *	Description:
     *       Loads data on resume
     *	Parameter: Not receive anything
     *	Return: None
     */
    @Override
    public void onResume() {
        super.onResume();

        /* Check whether the user has been confirmed their ticket
         *  and started to choose their destination again
         */
        if(mReset == true && mCanGoNextState == false)
        {
            mDestination.setText("");
            mStartDate.setText("");
            mEndDate.setText("");
            mNumAdultsSeekBar.setProgress(0);
            mNumChildSeekBar.setProgress(0);
        }

    }

    /*
     *	Function: onCreateOptionsMenu(Menu menu)
     *	Description:
     *       The purpose of this function is to create option menu
     *	Parameter: Menu menu: the menu widget to inflate to the activity's layout
     *	Return: boolean: Return true if menu is added to action bar successful. Otherwise, false
     */
    @SuppressLint("RestrictedApi") // Igor's code had this part, but i'm not sure what it's for
    // - alex, feb 13
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu_layout, menu);
        menu.getItem(confirm_ticket_item).setEnabled(false);
        return true;
    }


    /*
     *	Function: onOptionsItemSelected(MenuItem item)
     *	Description:
     *       The purpose of this function is to create event handler for menu items. Each menu items,
     *          will direct to a different layout
     *	Parameter: MenuItem item: the menu item on selected
     *	Return: boolean: Return true if menu item directs to another layout. Otherwise, false
     */
    // I added the following method so that we can move between activities using the menu.
    // This code was borrowed from Igor's sample code. -alex, feb 13
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;
        Intent intent = null;
        View view = this.getCurrentFocus();

        // check whether user can go to next activity
        if (checkInput() != true || mCanGoNextState == false) {
            result = false;
            hideKeyboard(view);
            Snackbar mySnackBar = Snackbar.make(findViewById(R.id.home_layout),
                    R.string.required_inputs,
                    Snackbar.LENGTH_SHORT);
            mySnackBar.show();
            return result;
        }

        switch (item.getItemId()) {
            case R.id.welcome_layout:
                hideKeyboard(view);
                Toast.makeText(this,
                        R.string.home_layout_label,
                        Toast.LENGTH_SHORT).show();
                result = true;
                break;
            case R.id.hotels_layout:
                /*
                    using intent flag to switch between activities if the activity exited. Otherwise,
                    start new activity. Thus, we can keep previous instance state
                */
                intent = new Intent(this, ChooseHotelActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                Toast.makeText(this,
                        R.string.choose_hotel_layout_label,
                        Toast.LENGTH_SHORT).show();
                result = true;
                break;
            case R.id.confirm_layout:
                intent = new Intent(this, TicketConfirm.class);
                startActivity(intent);
                Toast.makeText(this,
                        R.string.confirm_layout_label,
                        Toast.LENGTH_SHORT).show();
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }

    /*
     *	Function: getDate(EditText dayToBeSelected)
     *	Description:
     *       The purpose of this function is to get the beginning date of the trip or
     *           the end date of the trip
     *	Parameter: EditText dayToBeSelected: the EditText widget for selecting the beginning date or
     *                                           the end date of the trip
     *	Return: void: Not return anything
     */
    public void getDate(EditText dayToBeSelected) {
        final Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        if (!mStartDate.getText().toString().equals("") && dayToBeSelected.getId() == R.id.endDate) {
            mEndDate.setText("");
            String[] startingDate = mStartDate.getText().toString().split("/");

            cal.set(Calendar.YEAR, Integer.parseInt(startingDate[0].toString()));
            cal.set(Calendar.MONTH, Integer.parseInt(startingDate[1].toString()));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(startingDate[2].toString()));

            day = cal.get(Calendar.DAY_OF_MONTH);
            month = cal.get(Calendar.MONTH) - 1;
            year = cal.get(Calendar.YEAR);
        }
        else if (!mEndDate.getText().toString().equals("") && dayToBeSelected.getId() == R.id.startDate)
        {
            mEndDate.setText("");
        }

        mDatePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (checkDate(dayToBeSelected, year, month, dayOfMonth) != true) {
                            dayToBeSelected.setHint(R.string.chooseDate_label);
                            return;
                        }
                        dayToBeSelected.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                    }
                }, year, month, day);

        mDatePickerDialog.show();
    }

    /*
     *	Function: checkDate(EditText dayToBeSelected)
     *	Description:
     *       The purpose of this function is to check whether the beginning date of the trip or
     *           the end date of the trip is valid
     *	Parameter: EditText dayToBeSelected : the EditText widget for selecting the beginning date or
     *                                           the end date of the trip
     *             int year                 : Year to be checked
     *             int month                : Month to be checked
     *             int day                  : Day to be checked
     *	Return: boolean: return true of the date is valid. Otherwise, false
     */
    public boolean checkDate(EditText dayToBeSelected, int year, int month, int day) {
        boolean bRetCode = true;
        final Calendar currCal = Calendar.getInstance();
        final Calendar cal = Calendar.getInstance();
        Date currDate = null;
        String sStartDate = null;
        Date startDate = null;
        Date newDate = null;

        // check whether the date is checking is for start date or end date
        if (dayToBeSelected.getId() == R.id.startDate) {
            // check whether the start date is valid
            cal.set(year, month, day);
            newDate = cal.getTime();        // convert the selected date into a Date object

            currDate = currCal.getTime();   // convert the current calendar into a Date object

            // start date needs to be greater than (or equal to) the current date
            if (currDate.compareTo(newDate) > 0) {
                Snackbar mySnackBar = Snackbar.make(findViewById(R.id.home_layout), R.string.startDate_error_msg, Snackbar.LENGTH_SHORT);
                mySnackBar.show();
                bRetCode = false;
            }
        } else {
            // check if end date is valid
            mStartDate = (EditText) findViewById(R.id.startDate);
            sStartDate = mStartDate.getText().toString();

            cal.set(year, month, day);      // convert selected end date into a Date object
            newDate = cal.getTime();

            // convert start date into a date object
            try {
                startDate = new SimpleDateFormat("yyyy/MM/dd").parse(sStartDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // check whether the start date is empty
            if (sStartDate.isEmpty()) {
                bRetCode = false;
                return bRetCode;
            }

            // end date needs to be greater than (or equal to) the start date
            if (startDate.compareTo(newDate) > 0) {
                Snackbar mySnackBar = Snackbar.make(findViewById(R.id.home_layout), R.string.endDate_error_msg, Snackbar.LENGTH_SHORT);
                mySnackBar.show();
                bRetCode = false;
            }
        }

        return bRetCode;
    }

    /*
     *	Function: checkInput()
     *	Description:
     *       The purpose of this function is to check whether all the inputs is filled
     *	Parameter: Not receive anything
     *	Return: boolean: return true of the all of the inputs are filled. Otherwise, false
     */
    public boolean checkInput() {
        if (this.getDestination().getText().toString().isEmpty() ||
                this.getStartDate().getText().toString().isEmpty() ||
                this.getEndDate().getText().toString().isEmpty()) {
            Snackbar mySnackBar = Snackbar.make(findViewById(R.id.home_layout),
                    R.string.required_inputs,
                    Snackbar.LENGTH_SHORT);
            mySnackBar.show();
            return false;
        }

        // check whether the num of adults are valid
        if (Integer.parseInt(this.getNumAdults().getText().toString()) == 0) {
            Snackbar mySnackBar = Snackbar.make(findViewById(R.id.home_layout),
                    R.string.minNumAdults_message,
                    Snackbar.LENGTH_SHORT);
            mySnackBar.show();
            return false;
        }

        return true;
    }

    /*
     *	Function: hideKeyboard(View view)
     *	Description:
     *       The purpose of this function is to hide the keyboard of an EditText widget after clicking
     *          outside of the EditText widget
     *	Parameter: View view: the view to be processed
     *	Return: void: Not return anything
     */
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

}
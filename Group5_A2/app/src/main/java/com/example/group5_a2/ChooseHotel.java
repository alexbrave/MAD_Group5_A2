/*
*	PROJECT: PROG3150 - ASSIGNMENT 1
*	FILE: TicketConfirm.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2020/04/08
*	DESCRIPTION:
		This file contains ChooseHotel java class which is a subclass of AppCompatActivity and implemnts View.OnClickListener.
		The purpose of this class is to display hotels to the user.
*/
package com.example.group5_a2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


/*
 *  NAME : ChooseHotel
 *  PURPOSE : The purpose of this class is to display the interface for hotel selection.
 */
public class ChooseHotel extends AppCompatActivity implements View.OnClickListener{

    Button hotel1_button = null;
    Button hotel2_button = null;
    Button hotel3_button = null;

    private final int NONE = 0;
    private final String EMPTY = "";
    private final int hotel1 = 1;
    private final int hotel2 = 2;
    private final int hotel3 = 3;
    private final int welcome_screen_item = 0;
    private final int choose_hotel_item = 1;
    private final int confirm_ticket_item = 2;

    private LinearLayout choose_hotel_options;
    private TextView choose_hotel_error;


    // Shared Preferences values/variables
    private SharedPreferences savedValues;
    private final String sharedPrefsName = "Saved Values";
    private final String sharedDestination = "destination";
    private final String sharedStartDate = "start_date";
    private final String sharedEndDate = "end_date";
    private final String sharedNumOfAdults = "number_of_adults";
    private final String sharedNumOfChildren = "number_of_children";
    private final String sharedHotelChoice = "chosen_hotel";
    // define instance variables
    private String destination = EMPTY;
    private int number_of_guests = NONE;
    private int chosen_hotel = NONE;

    /*
     *	Function: onCreate(Bundle savedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the ChooseHotel class. And create
     *          event handler for widgets on the choose_hotel_layout layout
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_hotel_layout);

        savedValues = getSharedPreferences(sharedPrefsName, MODE_PRIVATE);

        choose_hotel_options = (LinearLayout) findViewById(R.id.choose_hotel_options);
        choose_hotel_error = (TextView) findViewById(R.id.choose_hotel_error);

        LoadData();

        if(destination.equals(EMPTY) || number_of_guests == NONE){
            choose_hotel_options.setVisibility(View.GONE);
            choose_hotel_error.setVisibility(View.VISIBLE);
        }
        else {
            choose_hotel_error.setVisibility(View.GONE);
            choose_hotel_options.setVisibility(View.VISIBLE);
            // Set the chosen destination to each hotel
            TextView hotel1_dest = (TextView)findViewById(R.id.hotel1_location);
            hotel1_dest.setText(destination);
            TextView hotel2_dest = (TextView)findViewById(R.id.hotel2_location);
            hotel2_dest.setText(destination);
            TextView hotel3_dest = (TextView)findViewById(R.id.hotel3_location);
            hotel3_dest.setText(destination);

            // Set the number of guests to the number accommodated by each hotel
            TextView hotel1_guests = (TextView)findViewById(R.id.hotel1_num_of_guests);
            hotel1_guests.setText(String.valueOf(number_of_guests));
            TextView hotel2_guests = (TextView)findViewById(R.id.hotel2_num_of_guests);
            hotel2_guests.setText(String.valueOf(number_of_guests));
            TextView hotel3_guests = (TextView)findViewById(R.id.hotel3_num_of_guests);
            hotel3_guests.setText(String.valueOf(number_of_guests));
        }

        // Hook up buttons to event handlers

        hotel1_button = (Button)findViewById(R.id.select_hotel1);
        hotel1_button.setOnClickListener(this);
        hotel2_button = (Button)findViewById(R.id.select_hotel2);
        hotel2_button.setOnClickListener(this);
        hotel3_button = (Button)findViewById(R.id.select_hotel3);
        hotel3_button.setOnClickListener(this);
    }

    /*
     *	Function: onCreateOptionsMenu(Menu menu)
     *	Description:
     *       The purpose of this function is to create an options menu
     *	Parameter: Menu menu: Menu that is selected that is being created
     *	Return: boolean: returns true/success
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
     *	Function: onSaveInstanceState(MenuItem item)
     *	Description:
     *       The purpose of this function is to switch screens when the menu item is selected
     *	Parameter: MenuItem item: Menu Item that is selected
     *	Return: void: Not return anything
     */
    // This code was borrowed from Igor's sample code. -alex, feb 13
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;
        Intent intent = null;
        int ID_of_choice = item.getItemId();
        if(ID_of_choice == R.id.welcome_layout){
            /* when user switch back to home layout, they have to hit the search hotels button again. Otherwise
                they can not move to choose hotels layout
            */
            MainActivity.mCanGoNextState = false;
            intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish(); // close the choose hotel layout since they redirect back to the home layout
            result = true;
        }
        if(ID_of_choice == R.id.confirm_layout) {
            intent = new Intent(this, TicketConfirm.class);
            startActivity(intent);
            result = true;
        }
        return result;
    }

    /*
     *	Function: onClick(View v)
     *	Description:
     *       Saves data on pause
     *	Parameter: View v : View that is triggering event
     *	Return: None
     */
    @Override
    public void onClick(View v) {

        Intent intent = null;
        int ID = v.getId();
        if(ID == R.id.select_hotel1){
            chosen_hotel = hotel1;
        }
        else if(ID == R.id.select_hotel2){
            chosen_hotel = hotel2;
        }
        else if(ID == R.id.select_hotel3){
            chosen_hotel = hotel3;
        }

        // Now we want to save all our data
        SaveData();

        intent = new Intent(this, TicketConfirm.class);
        startActivity(intent);
    }

    /*
     *	Function: onPause()
     *	Description:
     *       Saves data on pause
     *	Parameter: Not receive anything
     *	Return: None
     */
    @Override
    public void onPause() {

        SaveData();
        super.onPause();
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

        LoadData();

    }


    /*
     *	Function: SaveData()
     *	Description:
     *       The purpose of this function is to save the saved preferences
     *	Parameter: Not receive anything
     *	Return: None
     */
    private void SaveData() {
        // save the instance variables

        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("destination", destination);
        editor.putInt("number_of_guests", number_of_guests);
        editor.putInt("chosen_hotel", chosen_hotel);

        editor.apply();
    }


    /*
     *	Function: LoadData()
     *	Description:
     *       The purpose of this function is to load saved preferences
     *	Parameter: Not receive anything
     *	Return: None
     */
    private void LoadData() {
        destination = savedValues.getString(sharedDestination, EMPTY);
        number_of_guests = savedValues.getInt(sharedNumOfAdults, NONE);
        number_of_guests += savedValues.getInt(sharedNumOfChildren, NONE);
        chosen_hotel = savedValues.getInt(sharedHotelChoice, NONE);
    }
}

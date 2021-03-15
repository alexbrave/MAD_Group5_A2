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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


/*
 *  NAME : ChooseHotel
 *  PURPOSE : The purpose of this class is to display the interface for hotel selection.
 */
public class ChooseHotel extends AppCompatActivity {
    private final int NONE = 0;
    private final String EMPTY = "";
    private final int welcome_screen_item = 0;
    private final int choose_hotel_item = 1;
    private final int confirm_ticket_item = 2;
    private final String TAG = "ChooseHotel";

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

    // Fragments
    FragmentManager fm = null;
    Fragment fragment = null;

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
        setContentView(R.layout.choose_hotels_container_layout);

        savedValues = getSharedPreferences(sharedPrefsName, MODE_PRIVATE);

        // check whether the action bar is null
        if( getSupportActionBar() != null )
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // display back button
        }

        fm = getSupportFragmentManager();

        fragment = new ChooseHotelFragment();
        fm.beginTransaction()
                .add(R.id.choose_hotels_fragment_container, fragment)
                .commit();

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

    /*
     *	Function: onSupportNavigateUp()
     *	Description:
     *       The purpose of this function is to implement backward functionality
     *	Parameter: Not receive anything
     *	Return: None
     */
    @Override
    public boolean onSupportNavigateUp() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
        return true;
    }

}

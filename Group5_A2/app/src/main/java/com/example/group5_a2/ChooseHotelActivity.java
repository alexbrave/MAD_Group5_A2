/*
*	PROJECT: PROG3150 - ASSIGNMENT 2
*	FILE: ChooseHotelActivity.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2021/03/15
*	DESCRIPTION:
		This file contains ChooseHotelActivity java class which is a subclass of AppCompatActivity.
		The purpose of this class is to host 2 fragments which are ChooseHotelFragment and ChooseHotelDetailFragment class
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.group5_a2.ChooseHotelFragment;
import com.example.group5_a2.MainActivity;
import com.example.group5_a2.R;
import com.example.group5_a2.TicketConfirm;


/*
 *  NAME : ChooseHotel
 *  PURPOSE : The purpose of this class is to host host 2 fragments which are ChooseHotelFragment and ChooseHotelDetailFragment class
 */
public class ChooseHotelActivity extends AppCompatActivity {
    private final int NONE = 0;
    private final String EMPTY = "";
    private final int welcome_screen_item = 0;
    private final int choose_hotel_item = 1;
    private final String TAG = "ChooseHotel";

    public static boolean mAreHotelsDownloaded = false;

    // Fragments
    FragmentManager fm = null;
    Fragment fragment = null;


    /*
     *	Function: onCreate(Bundle savedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the ChooseHotel class. And host
     *         the ChooseHotelFragment
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_hotels_container_layout);

        mAreHotelsDownloaded = true; // list of hotels are ready

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
        int confirm_ticket_item = 2;
        menu.getItem(confirm_ticket_item).setEnabled(false);
        return true;
    }


    /*
     *	Function: onOptionsItemSelected(MenuItem item)
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
        if(ID_of_choice == R.id.hotels_layout) {
            /* check whether the current screen is hotel. If the current stack is hotel screen,
            *  confirm. Otherwise, go back to hotel screen or fragment
            */
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
                result = true;
            }
            else
            {
                Toast.makeText(this,
                        R.string.choose_hotel_layout_label,
                        Toast.LENGTH_SHORT).show();
            }
        }
        if(ID_of_choice == R.id.confirm_layout) {
            intent = new Intent(this, TicketConfirm.class);
            startActivity(intent);
            result = true;
        }
        return result;
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
        /* Check whether the current screen is the ChooseHotelFragment*/
        if (fm.getBackStackEntryCount() > 0) {
            // if not, it means currently at the hotel detail screen so go back to the ChooseHotelFragment which is a list of hotels
            fm.popBackStack();
        } else {
            /* if currently at ChooseHotelFragment, check whether the list of hotel is already downloaded.
            *  If the list of hotel is already downloaded, go back to the first screen. Otherwise, go back to loading screen
            */
            if(mAreHotelsDownloaded == true)
            {
                /* when user switch back to home layout, they have to hit the search hotels button again. Otherwise
                *  they can not move to choose hotels layout
                */
                MainActivity.mCanGoNextState = false; // reset
                mAreHotelsDownloaded = false; // reset
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish(); // close the choose hotel layout since they redirect back to the home layout
            }
            else
            {
                super.onBackPressed();
            }

        }
        return true;
    }

}

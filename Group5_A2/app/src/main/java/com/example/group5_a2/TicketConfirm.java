/*
*	PROJECT: PROG3150 - ASSIGNMENT 2
*	FILE: TicketConfirm.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2021/03/19
*	DESCRIPTION:
		This file contains TicketConfirm java class which is a subclass of AppCompatActivity. The purpose of this class is to handle information
        in regards to ticket confirmation.

*/
package com.example.group5_a2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;


/*
 *  NAME : TicketConfirm
 *  PURPOSE : The purpose of this class is to handle the confirmation for tickets.
 */
public class TicketConfirm extends AppCompatActivity {
    // define instance variables
    private String startDate;
    private String endDate;
    private boolean agreement = false;
    private int numberOfAdults;
    private int numberOfChildren;
    private String mChosenHotel;
    private String mChosenHotelImage;
    private Button confirmButton;
    private Button writeReviewButton;


    // Shared Preferences values/variables
    private SharedPreferences savedValues;

    // model for the TicketConfirm controller
    private TicketConfirmModel ticketConfirmModel;


    /*
    *	Function: onCreate(@Nullable Bundle SavedInstanceState)
    *	Description:
    *       The purpose of this function is to create an instance of the TicketConfirm class.
    *	Parameter: Bundle savedInstanceState: The state of the instance
    *	Return: void: Not return anything
    */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_confirm);

        ticketConfirmModel = new TicketConfirmModel();

        savedValues = getSharedPreferences(ticketConfirmModel.getTAGSharedPrefs(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = savedValues.edit();

        confirmButton = (Button) findViewById(R.id.confirm_btn);
        writeReviewButton = (Button) findViewById(R.id.write_review);

        //Load Data from SharedPreferences
        LoadData();

        ImageView hotelIV = (ImageView) findViewById(R.id.hotel_image);
        TextView hotelName = (TextView) findViewById(R.id.hotel_name);

        // check whether the action bar is null
        if( getSupportActionBar() != null )
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // display back button
        }

        //Setting string contents for display
        if (!startDate.equals(ticketConfirmModel.getEMPTY()) && !endDate.equals(ticketConfirmModel.getEMPTY()) && !mChosenHotel.equals(ticketConfirmModel.getEMPTY())
                && !mChosenHotelImage.equals(ticketConfirmModel.getEMPTY()) && numberOfAdults != ticketConfirmModel.getNONE())
        {
            Glide.with(this).load(mChosenHotelImage).placeholder(R.drawable.hotel1_image).into(hotelIV);
            hotelIV.setContentDescription(mChosenHotel);
            hotelName.setText(mChosenHotel);
            TextView startDateTV = (TextView) findViewById(R.id.startDate_view);
            startDateTV.setText(startDate + " - ");
            TextView endDateTV = (TextView) findViewById(R.id.endDate_view);
            endDateTV.setText(endDate);
            TextView numOfAdultsTV = (TextView) findViewById(R.id.numOfAdults_view);
            numOfAdultsTV.setText(String.valueOf(numberOfAdults) + " Adults");
            TextView numOfChildrenTV = (TextView) findViewById(R.id.numOfChildren_view);
            numOfChildrenTV.setText(String.valueOf(numberOfChildren) + " Children");
        }

        //Click Listener for confirmation
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check term agreements
                agreement = ((CheckBox) findViewById(R.id.checkbox)).isChecked();
                if (!agreement) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.agree_not_checked, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                // Confirm successful and back to the beginning
                Intent intent = new Intent(TicketConfirm.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
                Toast.makeText(TicketConfirm.this,
                        R.string.success,
                        Toast.LENGTH_SHORT).show();

                // Reset everything including preferences
                editor.clear();
                editor.commit();
                MainActivity.mCanGoNextState = false;
                MainActivity.mReset = true;
            }
        });

        // Allow the user to write a review
        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketConfirm.this,
                        UserReviewActivity.class);
                startActivity(intent);
            }
        });

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
            this.onSupportNavigateUp();
        }
        if(ID_of_choice == R.id.confirm_layout) {
            Toast.makeText(this, R.string.confirm_layout_label, Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    /*
     *	Function: LoadData()
     *	Description:
     *       The purpose of this function is to load saved preferences
     *	Parameter: Not receive anything
     *	Return: None
     */
    private void LoadData() {
        startDate = savedValues.getString(ticketConfirmModel.getTAGSharedStartDate(), ticketConfirmModel.getEMPTY());
        endDate = savedValues.getString(ticketConfirmModel.getTAGSharedEndDate(), ticketConfirmModel.getEMPTY());
        numberOfAdults = savedValues.getInt(ticketConfirmModel.getTAGSharedNumAdults(), ticketConfirmModel.getNONE());
        numberOfChildren = savedValues.getInt(ticketConfirmModel.getTAGSSharedNumChildren(), ticketConfirmModel.getNONE());
        mChosenHotel = getIntent().getStringExtra(ticketConfirmModel.getHOTEL_NAME_KEY());
        mChosenHotelImage = getIntent().getStringExtra(ticketConfirmModel.getHOTEL_IMAGE_KEY());
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
        super.onBackPressed();
        return true;
    }
}



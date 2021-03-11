/*
*	PROJECT: PROG3150 - ASSIGNMENT 1
*	FILE: TicketConfirm.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2020/04/08
*	DESCRIPTION:
		This file contains TicketConfirm java class which is a subclass of AppCompatActivity. The purpose of this class is to handle information
        in regards to ticket confirmation.

*/
package com.example.group5_a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*
 *  NAME : TicketConfirm
 *  PURPOSE : The purpose of this class is to handle the confirmation for tickets.
 */
public class TicketConfirm extends AppCompatActivity {

    private final int NONE = 0;
    private final String EMPTY = "";
    private final int hotel1 = 1;
    private final int hotel2 = 2;
    private final int hotel3 = 3;
    private final int welcome_screen_item = 0;
    private final int choose_hotel_item = 1;
    private final int confirm_ticket_item = 2;


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
    private String startDate = EMPTY;
    private String endDate = EMPTY;
    private boolean agreement = false;
    private int numberOfAdults = NONE;
    private int numberOfChildren = NONE;
    private int chosenHotel = NONE;

    private Button confirmButton;

    /*
     *	Function: onCreate(Bundle savedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the TicketConfirm class. And create
     *          event handler for widgets on the ticket_confirm layout
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_confirm);

        savedValues = getSharedPreferences(sharedPrefsName, MODE_PRIVATE);

        confirmButton = (Button) findViewById(R.id.confirm_btn);

        //Load Data from SharedPreferences
        LoadData();

        ImageView hotelIV = (ImageView) findViewById(R.id.hotel_image);
        TextView hotelName = (TextView) findViewById(R.id.hotel_name);

        // If the user chose hotel 1 in the hotel-choosing activity
        if (chosenHotel == hotel1) {
            // Set hotel image and name
            hotelIV.setImageResource(R.drawable.hotel1_image);
            hotelName.setText(R.string.hotel1_name);
        }
        // If the user chose hotel 2 in the hotel-choosing activity
        else if (chosenHotel == hotel2) {
            // Set hotel image and name
            hotelIV.setImageResource(R.drawable.hotel2_image);
            hotelName.setText(R.string.hotel2_name);
        } else if (chosenHotel == hotel3) {
            // Set hotel image and name
            hotelIV.setImageResource(R.drawable.hotel3_image);
            hotelName.setText(R.string.hotel3_name);
        }

        //Setting string contents for display
        if (!startDate.equals(EMPTY) && !endDate.equals(EMPTY) &&
                numberOfAdults != NONE &&
                chosenHotel != NONE) {
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
                agreement = ((CheckBox) findViewById(R.id.chkBox)).isChecked();
                if (!agreement) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.agree_not_checked, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Intent intent = new Intent(TicketConfirm.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(TicketConfirm.this,
                        R.string.success,
                        Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }


    /*
     *	Function: LoadData()
     *	Description:
     *       The purpose of this function is to load saved preferences
     *	Parameter: Not receive anything
     *	Return: None
     */
    private void LoadData() {
        startDate = savedValues.getString(sharedStartDate, EMPTY);
        endDate = savedValues.getString(sharedEndDate, EMPTY);
        numberOfAdults = savedValues.getInt(sharedNumOfAdults, NONE);
        numberOfChildren = savedValues.getInt(sharedNumOfChildren, NONE);
        chosenHotel = savedValues.getInt(sharedHotelChoice, NONE);
    }
}

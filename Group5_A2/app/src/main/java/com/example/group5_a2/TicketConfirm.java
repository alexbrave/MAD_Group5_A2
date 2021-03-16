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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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


/*
 *  NAME : TicketConfirm
 *  PURPOSE : The purpose of this class is to handle the confirmation for tickets.
 */
public class TicketConfirm extends Fragment {

    private final int NONE = 0;
    private final String EMPTY = "";
    private final int hotel1 = 1;
    private final int hotel2 = 2;
    private final int hotel3 = 3;
    private final int welcome_screen_item = 0;
    private final int choose_hotel_item = 1;
    private final int confirm_ticket_item = 2;
    private final String TAG = "TicketConfirm";

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

    FragmentManager fm = null;

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
        fm = getActivity().getSupportFragmentManager();
    }

    /*
     *	Function: onCreate(@Nullable Bundle SavedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the TicketConfirm class.
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.ticket_confirm, container, false);
        savedValues = getActivity().getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = savedValues.edit();

        confirmButton = (Button) mainView.findViewById(R.id.confirm_btn);

        //Load Data from SharedPreferences
        LoadData();

        ImageView hotelIV = (ImageView) mainView.findViewById(R.id.hotel_image);
        TextView hotelName = (TextView) mainView.findViewById(R.id.hotel_name);

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
            TextView startDateTV = (TextView) mainView.findViewById(R.id.startDate_view);
            startDateTV.setText(startDate + " - ");
            TextView endDateTV = (TextView) mainView.findViewById(R.id.endDate_view);
            endDateTV.setText(endDate);
            TextView numOfAdultsTV = (TextView) mainView.findViewById(R.id.numOfAdults_view);
            numOfAdultsTV.setText(String.valueOf(numberOfAdults) + " Adults");
            TextView numOfChildrenTV = (TextView) mainView.findViewById(R.id.numOfChildren_view);
            numOfChildrenTV.setText(String.valueOf(numberOfChildren) + " Children");
        }

        //Click Listener for confirmation
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check term agreements
                agreement = ((CheckBox) mainView.findViewById(R.id.chkBox)).isChecked();
                if (!agreement) {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), R.string.agree_not_checked, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Intent intent = new Intent(TicketConfirm.this.getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(TicketConfirm.this.getActivity(),
                        R.string.success,
                        Toast.LENGTH_SHORT).show();

                // Reset everything including preferences
                editor.clear();
                editor.commit();
                MainActivity.mCanGoNextState = false;
                MainActivity.mReset = true;
            }
        });
        return mainView;
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



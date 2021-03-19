/*
*	PROJECT: PROG3150 - ASSIGNMENT 2
*	FILE: ChooseHotelController.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2021/03/15
*	DESCRIPTION:
		This file contains ChooseHotelController java. The purpose of this class is to contain the logic of 2 Fragments ChooseHotelFragment and ChooseHotelDetailFragment
*/

package com.example.group5_a2;

import android.content.Context;
import android.content.SharedPreferences;

/*
 *  NAME : ChooseHotelController
 *  PURPOSE : The purpose of this class is to to contain the logic of 2 Fragments ChooseHotelFragment and ChooseHotelDetailFragment
 */
public class ChooseHotelController {
    private final int NONE = 0;
    private final String EMPTY = "";

    // Shared Preferences values/variables
    private final String sharedPrefsName = "Saved Values";
    private final String sharedDestination = "destination";
    private final String sharedStartDate = "start_date";
    private final String sharedEndDate = "end_date";
    private final String sharedNumOfAdults = "number_of_adults";
    private final String sharedNumOfChildren = "number_of_children";
    private final String sharedHotelChoice = "chosen_hotel";

    // define instance variables
    private String mDestination = EMPTY;
    private int mNumber_of_guests = NONE;

    public String getDestination() {
        return mDestination;
    }

    public int getNumber_of_guests() {
        return mNumber_of_guests;
    }
    
    /*
     *	Function: LoadData()
     *	Description:
     *       The purpose of this function is to load saved preferences
     *	Parameter: SharedPreferences savedValues : the shared preferences in the application
     *	Return: None
     */
    public void LoadData(SharedPreferences savedValues) {
        mDestination = savedValues.getString(sharedDestination, EMPTY);
        mNumber_of_guests = savedValues.getInt(sharedNumOfAdults, NONE);
        mNumber_of_guests += savedValues.getInt(sharedNumOfChildren, NONE);
    }
}

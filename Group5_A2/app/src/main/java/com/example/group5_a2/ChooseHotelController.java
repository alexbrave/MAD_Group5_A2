package com.example.group5_a2;

import android.content.Context;
import android.content.SharedPreferences;

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
    private int mChosen_hotel = NONE;

    public String getDestination() {
        return mDestination;
    }

    public int getNumber_of_guests() {
        return mNumber_of_guests;
    }

    public int getChosen_hotel() {
        return mChosen_hotel;
    }


    /*
     *	Function: SaveData()
     *	Description:
     *       The purpose of this function is to save the saved preferences
     *	Parameter: SharedPreferences savedValues : the shared preferences in the application
     *	Return: None
     */
    public void SaveData(SharedPreferences savedValues) {
        // save the instance variables

        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("destination", mDestination);
        editor.putInt("number_of_guests", mNumber_of_guests);
        editor.putInt("chosen_hotel", mChosen_hotel);

        editor.apply();
    }

    /*
     *	Function: SaveData(SharedPreferences savedValues, int iChosenHotel)
     *	Description:
     *       The purpose of this function is to save the saved preferences, especially for the hotel that is chosen
     *	Parameter: SharedPreferences savedValues : the shared preferences in the application
     *              int iChosenHotel             : The chosen hotel
     *	Return: None
     */
    public void SaveData(SharedPreferences savedValues, int iChosenHotel) {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putInt("chosen_hotel", mChosen_hotel);
        editor.apply();
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
        mChosen_hotel = savedValues.getInt(sharedHotelChoice, NONE);
    }
}

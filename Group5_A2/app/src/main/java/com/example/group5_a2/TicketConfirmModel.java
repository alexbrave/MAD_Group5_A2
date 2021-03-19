package com.example.group5_a2;

import android.content.SharedPreferences;

public class TicketConfirmModel {

    // constants used by TicketConfirm
    private final int NONE = 0;
    private final String EMPTY = "";
    private final int hotel1 = 1;
    private final int hotel2 = 2;
    private final int hotel3 = 3;
    private final int welcome_screen_item = 0;
    private final int choose_hotel_item = 1;
    private final int confirm_ticket_item = 2;
    private final String TAG = "TicketConfirm";

    // key names for saving values when the hotel is being selected for reviewing
    private final String HOTEL_NAME_KEY = "hotel_name";
    private final String HOTEL_IMAGE_KEY = "hotel_image";
    private final String sharedPrefsName = "Saved Values";
    private final String sharedDestination = "destination";
    private final String sharedStartDate = "start_date";
    private final String sharedEndDate = "end_date";
    private final String sharedNumOfAdults = "number_of_adults";
    private final String sharedNumOfChildren = "number_of_children";
    private final String sharedHotelChoice = "chosen_hotel";


    /*
     *	Function: getNONE()
     *	Description:
     *       Returns the NONE member constant
     *	Parameter: void
     *	Return: int: NONE constant
     */
    public int getNONE() {
        return NONE;
    }

    /*
     *	Function: getEMPTY()
     *	Description:
     *       Returns the EMPTY member constant
     *	Parameter: void
     *	Return: String: EMPTY constant
     */
    public String getEMPTY() { return EMPTY; }

    /*
     *	Function: getHotel1()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: int: hotel1 constant
     */
    public int getHotel1() { return hotel1; }

    /*
     *	Function: getHotel2()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: int: hotel2 constant
     */
    public int getHotel2() { return hotel2; }

    /*
     *	Function: getHotel3()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: int: hotel3 constant
     */
    public int getHotel3() { return hotel3; }

    /*
     *	Function: getWelcome_screen_item()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: int: welcome_screen_item
     */
    public int getWelcome_screen_item() { return welcome_screen_item; }

    /*
     *	Function: getChoose_hotel_item()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: int: choose_hotel_item
     */
    public int getChoose_hotel_item() { return choose_hotel_item; }

    /*
     *	Function: getConfirm_ticket_item()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: int: confirm_ticket_item
     */
    public int getConfirm_ticket_item() { return confirm_ticket_item; }

    /*
     *	Function: getTAG()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: String: TAG
     */
    public String getTAG() { return TAG; }

    /*
     *	Function: getHOTEL_NAME_KEY()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: String: HOTEL_NAME_KEY
     */
    public String getHOTEL_NAME_KEY() { return HOTEL_NAME_KEY; }

    /*
     *	Function: getHOTEL_IMAGE_KEY()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: void
     *	Return: String: HOTEL_IMAGE_KEY
     */
    public String getHOTEL_IMAGE_KEY() { return HOTEL_IMAGE_KEY; }

    /*
     *	Function: getTAGSharedPrefs()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for the name of the shared preference
     */
    public String getTAGSharedPrefs() {
        return sharedPrefsName;
    }

    /*
     *	Function: getTAGSharedDestination()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for destination
     */
    public String getTAGSharedDestination() {
        return sharedDestination;
    }

    /*
     *	Function: getTAGSharedStartDate()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for starting date
     */
    public String getTAGSharedStartDate() {
        return sharedStartDate;
    }

    /*
     *	Function: getTAGSharedEndDate()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for end date
     */
    public String getTAGSharedEndDate() {
        return sharedEndDate;
    }

    /*
     *	Function: getTAGSharedNumAdults()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for number of adults
     */
    public String getTAGSharedNumAdults() {
        return sharedNumOfAdults;
    }

    /*
     *	Function: getTAGSSharedNumChildren()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for number of children
     */
    public String getTAGSSharedNumChildren() {
        return sharedNumOfChildren;
    }


}

/*
*	PROJECT: PROG3150 - ASSIGNMENT 2
*	FILE: MainActivityModel.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2021/03/14
*	DESCRIPTION:
*		This file is the model class for the MainActivity controller
*/
package com.example.group5_a2;

/*
 *  NAME : MainActivityModel
 *  PURPOSE : The purpose of this class is contain data used in the MainActivity controller/view.
 */
public class MainActivityModel
{
    //Tags, Keys and Constants
    private final int NONE = 0;
    private final String EMPTY = "";
    private final String TAG = "MainActivity";
    private final String DESTINATION_KEY = "destination";
    private final String START_DATE_KEY = "startDate";
    private final String END_DATE_KEY = "endDate";
    private final String NUM_ADULTS_KEY = "numOfAdults";
    private final String NUM_CHILDREN_KEY = "numOfChildren";

    // Shared Preferences values/variables
    private final String sharedPrefsName = "Saved Values";
    private final String sharedDestination = "destination";
    private final String sharedStartDate = "start_date";
    private final String sharedEndDate = "end_date";
    private final String sharedNumOfAdults = "number_of_adults";
    private final String sharedNumOfChildren = "number_of_children";

    //Logical Variables
    private String mDestinationValue = EMPTY;
    private String mStartDateValue = EMPTY;
    private String mEndDateValue = EMPTY;
    private String mNumOfAdultsValue = EMPTY;
    private String mNumOfChildrenValue = EMPTY;

    /*
     *	Function: getDestinationKey()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: destination key
     */
    public String getDestinationKey()
    {
        return DESTINATION_KEY;
    }

    /*
     *	Function: getStartDateKey()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: start date key
     */
    public String getStartDateKey()
    {
        return START_DATE_KEY;
    }

    /*
     *	Function: getEndDateKey()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: end date key
     */
    public String getEndDateKey()
    {
        return END_DATE_KEY;
    }

    /*
     *	Function: getNumAdultsKey()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: number adults key
     */
    public String getNumAdultsKey()
    {
        return NUM_ADULTS_KEY;
    }

    /*
     *	Function: getNumChildrenKey()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: number children key
     */
    public String getNumChildrenKey()
    {
        return NUM_CHILDREN_KEY;
    }

    /*
     *	Function: getDestinationValue()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: destination value
     */
    public String getDestinationValue()
    {
        return mDestinationValue;
    }

    /*
     *	Function: setDestinationValue(String newValue)
     *	Description:
     *       The purpose of this is set a particular member value to a new one
     *	Parameter:
     *      String newValue : New value to set
     *	Return: void: none
     */
    public void setDestinationValue(String newValue)
    {
        mDestinationValue = newValue;
    }

    /*
     *	Function: getStartDateValue()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: start date value
     */
    public String getStartDateValue()
    {
        return mStartDateValue;
    }

    /*
     *	Function: setStartDateValue(String newValue)
     *	Description:
     *       The purpose of this is set a particular member value to a new one
     *	Parameter:
     *      String newValue : New value to set
     *	Return: void: none
     */
    public void setStartDateValue(String newValue)
    {
        mStartDateValue = newValue;
    }


    /*
     *	Function: getEndDateValue()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: end date value
     */
    public String getEndDateValue()
    {
        return mEndDateValue;
    }

    /*
     *	Function: setEndDateValue(String newValue)
     *	Description:
     *       The purpose of this is set a particular member value to a new one
     *	Parameter:
     *      String newValue : New value to set
     *	Return: void: none
     */
    public void setEndDateValue(String newValue)
    {
        mEndDateValue = newValue;
    }

    /*
     *	Function: getNumAdultsValue()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: number adults value
     */
    public String getNumAdultsValue()
    {
        return mNumOfAdultsValue;
    }

    /*
     *	Function: setNumAdultsValue(String newValue)
     *	Description:
     *       The purpose of this is set a particular member value to a new one
     *	Parameter:
     *      String newValue : New value to set
     *	Return: void: none
     */
    public void setNumAdultsValue(String newValue)
    {
        mNumOfAdultsValue = newValue;
    }

    /*
     *	Function: getNumChildrenValue()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: number of children value
     */
    public String getNumChildrenValue()
    {
        return mNumOfChildrenValue;
    }

    /*
     *	Function: setNumChildrenValue(String newValue)
     *	Description:
     *       The purpose of this is set a particular member value to a new one
     *	Parameter:
     *      String newValue : New value to set
     *	Return: void: none
     */
    public void setNumChildrenValue(String newValue)
    {
        mNumOfChildrenValue = newValue;
    }

    /*
     *	Function: getTAGSharedPrefs()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for the name of the shared preference
     */
    public String getTAGSharedPrefs()
    {
        return sharedPrefsName;
    }

    /*
     *	Function: getTAGSharedDestination()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for destination
     */
    public String getTAGSharedDestination()
    {
        return sharedDestination;
    }

    /*
     *	Function: getTAGSharedStartDate()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for starting date
     */
    public String getTAGSharedStartDate()
    {
        return sharedStartDate;
    }

    /*
     *	Function: getTAGSharedEndDate()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for end date
     */
    public String getTAGSharedEndDate()
    {
        return sharedEndDate;
    }

    /*
     *	Function: getTAGSharedNumAdults()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for number of adults
     */
    public String getTAGSharedNumAdults()
    {
        return sharedNumOfAdults;
    }

    /*
     *	Function: getTAGSSharedNumChildren()
     *	Description:
     *       The purpose of this is return a particular member value
     *	Parameter: None
     *	Return: String: SharedPref tag for number of children
     */
    public String getTAGSSharedNumChildren()
    {
        return sharedNumOfChildren;
    }

}

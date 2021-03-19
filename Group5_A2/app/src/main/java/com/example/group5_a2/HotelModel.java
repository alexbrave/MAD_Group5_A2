/*
*	PROJECT: PROG3150 - ASSIGNMENT 2
*	FILE: HotelModel.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2021/03/15
*	DESCRIPTION:
		This file contains HotelModel java. The purpose of this class is to contain the hotel model for the application
*/
package com.example.group5_a2;

/*
 *  NAME : HotelModel
 *  PURPOSE : The purpose of this class is to contain the hotel model for the application
 */
public class HotelModel {

    /*
     *	Function: Constructor - HotelModel(String hotelName, String hotelDescription, String hotelImage)
     *	Description:
     *       The purpose of this function is to create an instance of the HotelModel class
     *	Parameter: String hotelName : the hotel name
     *             String hotelDescription : the hotel description
     *             String hotelImage : the hotel image path
     *	Return: None
     */
    public HotelModel(String hotelName, String hotelDescription, String hotelImage) {
        mHotelName = hotelName;
        mHotelDescription = hotelDescription;
        mHotelImage = hotelImage;
    }

    /*
     *	Function: Getter - getHotelName()
     *	Description:
     *       The purpose of this function is to return the hotel name of the HotelModel instance
     *	Parameter: None
     *	Return: return the hotel name
     */
    public String getHotelName() {
        return mHotelName;
    }

    /*
     *	Function: Setter - setHotelName(String hotelName)
     *	Description:
     *       The purpose of this function is to set the hotel name of the HotelModel instance
     *	Parameter: String hotelName : the hotel name
     *	Return: None
     */
    public void setHotelName(String hotelName) {
        mHotelName = hotelName;
    }

    /*
     *	Function: Getter - getHotelDescription()
     *	Description:
     *       The purpose of this function is to return the hotel description of the HotelModel instance
     *	Parameter: None
     *	Return: return the hotel description
     */
    public String getHotelDescription() {
        return mHotelDescription;
    }

    /*
     *	Function: Setter - setHotelDescription(String hotelDescription)
     *	Description:
     *       The purpose of this function is to set the hotel description of the HotelModel instance
     *	Parameter: String hotelDescription : the hotel description
     *	Return: None
     */
    public void setHotelDescription(String hotelDescription) {
        mHotelDescription = hotelDescription;
    }

    /*
     *	Function: Getter - getHotelImage()
     *	Description:
     *       The purpose of this function is to return the hotel image path of the HotelModel instance
     *	Parameter: None
     *	Return: return the hotel image path
     */
    public String getHotelImage() {
        return mHotelImage;
    }

    /*
     *	Function: Setter - setHotelImage(String hotelImage)
     *	Description:
     *       The purpose of this function is to set the hotel image path of the HotelModel instance
     *	Parameter: String hotelImage : the hotel image path
     *	Return: None
     */
    public void setHotelImage(String hotelImage) {
        mHotelImage = hotelImage;
    }

    private String mHotelName = null;
    private String mHotelDescription = null;
    private String mHotelImage = null;
}

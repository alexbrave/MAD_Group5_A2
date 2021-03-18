package com.example.group5_a2;

public class Hotel {

    public Hotel(String hotelName, String hotelDescription, String hotelImage) {
        mHotelName = hotelName;
        mHotelDescription = hotelDescription;
        mHotelImage = hotelImage;
    }

    public String getHotelName() {
        return mHotelName;
    }

    public void setHotelName(String hotelName) {
        mHotelName = hotelName;
    }

    public String getHotelDescription() {
        return mHotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        mHotelDescription = hotelDescription;
    }

    public String getHotelImage() {
        return mHotelImage;
    }

    public void setHotelImage(String hotelImage) {
        mHotelImage = hotelImage;
    }

    private String mHotelName = null;
    private String mHotelDescription = null;
    private String mHotelImage = null;
}

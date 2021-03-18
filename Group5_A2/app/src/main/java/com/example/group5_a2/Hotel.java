package com.example.group5_a2;

public class Hotel {
    public int getHotelRating() {
        return mHotelRating;
    }

    public void setHotelRating(int hotelRating) {
        mHotelRating = hotelRating;
    }

    public int getHotelPrice() {
        return mHotelPrice;
    }

    public void setHotelPrice(int hotelPrice) {
        mHotelPrice = hotelPrice;
    }

    public Hotel(String hotelName, String hotelDescription, String hotelImage, int hotelRating, int hotelPrice) {
        mHotelName = hotelName;
        mHotelDescription = hotelDescription;
        mHotelImage = hotelImage;
        mHotelRating = hotelRating;
        mHotelPrice = hotelPrice;
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
    private int mHotelRating = 0;
    private int mHotelPrice = 0;
}

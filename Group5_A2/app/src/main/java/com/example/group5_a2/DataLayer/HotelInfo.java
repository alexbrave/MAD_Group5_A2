// insert file header comment

package com.example.group5_a2.DataLayer;

// This class represents a row from the hotelInfo table, which can be found here:
// https://1drv.ms/w/s!Au8Nlz7Yplbah7RX14DdQTBXB6_0ZA?e=B2v7jQ
public class HotelInfo {

    // These represent data from the columns of the hotelInfo table
    private String name;
    private String description;
    private String imageUrl;

    // not really used, might consider (alex) removing later
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    // This is the main constructor that should be used
    public HotelInfo(){
        // No logic for this constructor
    }

    // This constructor is not really used, since we first instantiate a HotelInfo object
    // then populate each field individually from data we get from the database
    public HotelInfo(String name, String description, String imageUrl) {
        this.name        = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    //  Private data member getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getImageUrl() { return imageUrl; }

    // Private data member setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}

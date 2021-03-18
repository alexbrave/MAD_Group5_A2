// insert file header comment

package com.example.group5_a2.DataLayer;

// This class represents a row from the bookedTicket table, which can be found here:
// https://1drv.ms/w/s!Au8Nlz7Yplbah7RX14DdQTBXB6_0ZA?e=B2v7jQ

public class BookedTicket {
    // These represent data from the columns of the bookedTicket table
    private int tripId;
    private int numAdults;
    private int numKids;
    private String destination;
    private String hotel;
    private String startDate;
    private String endDate;

    // not really used, might consider (alex) removing later
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    // This constructor is not really used, since we first instantiate a BookedTicket object
    // then populate each field individually from data we get from the database
    public BookedTicket(int tripId, int numAdults, int numKids,
                        String destination, String hotel,
                        String startDate, String endDate) {
        this.tripId = tripId;
        this.numAdults = numAdults;
        this.numKids = numKids;
        this.destination = destination;
        this.hotel = hotel;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // This is the main constructor that should be used
    public BookedTicket(){
        // No logic for this constructor
    }

    //  Private data member getters
    public int getTripId()          { return tripId; }
    public int getNumAdults()       { return numAdults; }
    public int getNumKids()         { return numKids; }
    public String getDestination()  { return destination; }
    public String getHotel()        { return hotel; }
    public String getStartDate()    { return startDate; }
    public String getEndDate()      { return endDate; }

    // Private data member setters
    public void setTripId(int tripId)               { this.tripId = tripId; }
    public void setNumAdults(int numAdults)         { this.numAdults = numAdults; }
    public void setNumKids(int numKids)             { this.numKids = numKids; }
    public void setDestination(String destination)  { this.destination = destination; }
    public void setHotel(String hotel)              { this.hotel = hotel; }
    public void setStartDate(String startDate)      { this.startDate = startDate; }
    public void setEndDate(String endDate)          { this.endDate = endDate; }
}

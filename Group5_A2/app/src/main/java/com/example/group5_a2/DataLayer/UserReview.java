// insert file header comment

package com.example.group5_a2.DataLayer;


// This class represents a row from the userReview table, which can be found here:
// https://1drv.ms/w/s!Au8Nlz7Yplbah7RX14DdQTBXB6_0ZA?e=B2v7jQ
public class UserReview {

    // These represent data from the columns of the userReview table
    private String name;
    private int    score;
    private String review;

    // not really used, might consider (alex) removing later
    public static final int TRUE = 1;
    public static final int FALSE = 0;


    // This constructor is not really used, since we first instantiate a UserReview object
    // then populate each field individually from data we get from the database
    public UserReview(String name, int score, String review) {
        this.name   = name;
        this.score  = score;
        this.review = review;
    }

    // This is the main constructor that should be used
    public UserReview(){
        // no logic for this constructor
    }

    //  Private data member getters
    public String getName()     { return name; }
    public int getScore()       { return score; }
    public String getReview()   { return review; }

    // Private data member setters
    public void setName(String name)        { this.name = name; }
    public void setScore(int score)         { this.score = score; }
    public void setReview(String review)    { this.review = review; }
}

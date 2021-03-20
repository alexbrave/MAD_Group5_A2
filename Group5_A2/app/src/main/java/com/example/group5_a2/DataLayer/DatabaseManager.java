// File header comment

package com.example.group5_a2.DataLayer;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.example.group5_a2.HotelModel;
import com.example.group5_a2.R;

public class DatabaseManager {

    // database constants
    public static final String DB_NAME = "Group5_A2.db";
    public static final int    DB_VERSION = 1;

    // Hotel Info table column names and associated column numbers
    public static final String HOTEL_INFO_TABLE = "hotelInfo";

    public static final String HOTEL_INFO_NAME = "name"; // primary key
    public static final int    HOTEL_INFO_NAME_COL = 0;

    public static final String HOTEL_INFO_DESCRIPTION = "description";
    public static final int    HOTEL_INFO_DESCRIPTION_COL = 1;

    public static final String HOTEL_INFO_IMAGE_URL = "imageUrl";
    public static final int    HOTEL_INFO_IMAGE_URL_COL = 2;

    // Booked Ticket column names and associated column numbers
    public static final String BOOKED_TICKET_TABLE = "bookedTicket";

    public static final String BOOKED_TICKET_TRIP_ID = "tripId"; // primary key
    public static final int    BOOKED_TICKET_TRIP_ID_COL = 0;

    public static final String BOOKED_TICKET_NUM_ADULTS = "numAdults";
    public static final int    BOOKED_TICKET_NUM_ADULTS_COL = 1;

    public static final String BOOKED_TICKET_NUM_KIDS = "numKids";
    public static final int    BOOKED_TICKET_NUM_KIDS_COL = 2;

    public static final String BOOKED_TICKET_DESTINATION = "destination";
    public static final int    BOOKED_TICKET_DESTINATION_COL = 3;

    public static final String BOOKED_TICKET_HOTEL = "hotel";
    public static final int    BOOKED_TICKET_HOTEL_COL = 4;

    public static final String BOOKED_TICKET_START_DATE = "startDate";
    public static final int    BOOKED_TICKET_START_DATE_COL = 5;

    public static final String BOOKED_TICKET_END_DATE = "endDate";
    public static final int    BOOKED_TICKET_END_DATE_COL = 6;

    // Hotel Info table column names and associated column numbers
    public static final String USER_REVIEW_TABLE = "userReview";

    public static final String USER_REVIEW_NAME = "name"; // primary key
    public static final int    USER_REVIEW_NAME_COL = 0;

    public static final String USER_REVIEW_SCORE = "score";
    public static final int    USER_REVIEW_SCORE_COL = 1;

    public static final String USER_REVIEW_REVIEW = "review";
    public static final int    USER_REVIEW_REVIEW_COL = 2;

    // CREATE and DROP TABLE statements
    public static final String CREATE_HOTEL_INFO_TABLE =
            "CREATE TABLE " + HOTEL_INFO_TABLE + " (" +
                    HOTEL_INFO_NAME         + " TEXT NOT NULL PRIMARY KEY, " +
                    HOTEL_INFO_DESCRIPTION  + " TEXT NOT NULL, " +
                    HOTEL_INFO_IMAGE_URL    + " TEXT NOT NULL);";

    public static final String CREATE_BOOKED_TICKET_TABLE =
            "CREATE TABLE " + BOOKED_TICKET_TABLE + " (" +
                    BOOKED_TICKET_TRIP_ID       + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    BOOKED_TICKET_NUM_ADULTS    + " INTEGER NOT NULL, " +
                    BOOKED_TICKET_NUM_KIDS      + " INTEGER NOT NULL, " +
                    BOOKED_TICKET_DESTINATION   + " TEXT    NOT NULL, " +
                    BOOKED_TICKET_HOTEL         + " TEXT    NOT NULL, " +
                    BOOKED_TICKET_START_DATE    + " TEXT    NOT NULL, " +
                    BOOKED_TICKET_END_DATE      + " TEXT    NOT NULL);";

    public static final String CREATE_USER_REVIEW_TABLE =
            "CREATE TABLE " + USER_REVIEW_TABLE + " (" +
                    USER_REVIEW_NAME    + " TEXT    NOT NULL PRIMARY KEY, " +
                    USER_REVIEW_SCORE   + " INTEGER NOT NULL, " +
                    USER_REVIEW_REVIEW  + " TEXT    NOT NULL);";

    public static final String DROP_HOTEL_INFO_TABLE =
            "DROP TABLE IF EXISTS " + HOTEL_INFO_TABLE;

    public static final String DROP_BOOKED_TICKET_TABLE =
            "DROP TABLE IF EXISTS " + BOOKED_TICKET_TABLE;

    public static final String DROP_USER_REVIEW_TABLE =
            "DROP TABLE IF EXISTS " + USER_REVIEW_TABLE;


    // This class simplifies interactions with the database, as far as I understand
    // I (alex) should put a class header here
    private static class DBHelper extends SQLiteOpenHelper {

        // DBHelper constructor
        public DBHelper(Context context, String name,
                        CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Interestingly, this method is called not when the DatabaseManager or
        // DBHelper are created, but when the first writeable or readable database
        // is called with openReadableDB() or openWritableDB()
        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_HOTEL_INFO_TABLE);
            db.execSQL(CREATE_BOOKED_TICKET_TABLE);
            db.execSQL(CREATE_USER_REVIEW_TABLE);

            db.execSQL("INSERT INTO hotelInfo VALUES " +
                    "('Grand Fiesta Resort', " +
                    "'Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                    "        Duis et viverra elit, et placerat eros. Mauris id ornare felis.\n" +
                    "        Integer purus massa, sollicitudin in massa vitae, porta ultrices ex.\n" +
                    "        Duis eu rhoncus odio. Aliquam vel placerat neque.\n" +
                    "        Suspendisse malesuada nisi at sem volutpat, eget mollis lorem iaculis.\n" +
                    "        Proin volutpat accumsan nulla nec aliquam.', " +
                    "'https://cdn.discordapp.com/attachments/822191686058115113/822191866279624704/img1.jpg')");
            db.execSQL("INSERT INTO hotelInfo VALUES " +
                    "('Chalet Belle Roche', " +
                    "'Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                    "        Duis et viverra elit, et placerat eros. Mauris id ornare felis.\n" +
                    "        Integer purus massa, sollicitudin in massa vitae, porta ultrices ex.\n" +
                    "        Duis eu rhoncus odio. Aliquam vel placerat neque.\n" +
                    "        Suspendisse malesuada nisi at sem volutpat, eget mollis lorem iaculis.\n" +
                    "        Proin volutpat accumsan nulla nec aliquam.', " +
                    "'https://cdn.discordapp.com/attachments/822191686058115113/822191890623234098/img2.jpg')");
            db.execSQL("INSERT INTO hotelInfo VALUES " +
                    "('Hotel Britannique', " +
                    "'Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                    "        Duis et viverra elit, et placerat eros. Mauris id ornare felis.\n" +
                    "        Integer purus massa, sollicitudin in massa vitae, porta ultrices ex.\n" +
                    "        Duis eu rhoncus odio. Aliquam vel placerat neque.\n" +
                    "        Suspendisse malesuada nisi at sem volutpat, eget mollis lorem iaculis.\n" +
                    "        Proin volutpat accumsan nulla nec aliquam.', " +
                    "'https://cdn.discordapp.com/attachments/822191686058115113/822191902576214074/img3.jpg')");

//            db.execSQL("INSERT INTO hotelInfo VALUES " +
//                    "('Another Hotel', 'Another description', 'anotherTestURL.com')");
//            db.execSQL("INSERT INTO hotelInfo VALUES " +
//                    "('Last Hotel', 'Last description', 'lastURL.com')");
        }

        // This method is not really necessary for us, but it exists in Igor's code, and it
        // does not seem to cause any problems
        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(DatabaseManager.DROP_HOTEL_INFO_TABLE);
            db.execSQL(DatabaseManager.DROP_BOOKED_TICKET_TABLE);
            db.execSQL(DatabaseManager.DROP_USER_REVIEW_TABLE);
            onCreate(db);
        }
    }

    // This is where the actual database and DBHelper variable are
    private SQLiteDatabase db;
    private DBHelper dbHelper;


    // I (alex) should probably add a header comment here
    // Again, this does not instantiate a database, the database is instantiated only
    // on the first the openReadableDB() or openWritableDB() methods are called
    public DatabaseManager(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    // This method should be called after every time the database is called
    public void closeDB() {
        if (db != null)
            db.close();
    }

    // This might be removed later, I'm (alex) not sure currently
    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }

    // This method will return an ArrayList of HotelInfo objects, that will be populated
    // with data from the database!
    public ArrayList<HotelInfo> getHotelInfos() {
        ArrayList<HotelInfo> hotelInfos = new ArrayList<HotelInfo>();
        openReadableDB();
        Cursor cursor = db.query(HOTEL_INFO_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            HotelInfo hI = new HotelInfo();
            hI.setName(cursor.getString(HOTEL_INFO_NAME_COL));
            hI.setName(cursor.getString(HOTEL_INFO_IMAGE_URL_COL));
            hI.setDescription(cursor.getString(HOTEL_INFO_DESCRIPTION_COL));

            hotelInfos.add(hI);
        }
        closeCursor(cursor);
        closeDB();

        return hotelInfos;
    }


    // This method will return an ArrayList of HotelInfo objects, that will be populated
    // with data from the database!
    public ArrayList<HotelModel> getHotelModels() {
        ArrayList<HotelModel> hotelModels = new ArrayList<HotelModel>();
        openReadableDB();
        String fileDirectory = Environment.getExternalStorageDirectory().toString() + "/MAD/";
        Cursor cursor = db.query(HOTEL_INFO_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            HotelModel hm = new HotelModel(cursor.getString(HOTEL_INFO_NAME_COL),
                    cursor.getString(HOTEL_INFO_DESCRIPTION_COL),
                    fileDirectory + "img" + String.valueOf(cursor.getPosition() + 1) + ".jpg");

            hotelModels.add(hm);
        }
        closeCursor(cursor);
        closeDB();

        return hotelModels;
    }

    // This method will return an ArrayList of BookedTicket objects, that will be populated
    // with data from the database!
    public ArrayList<BookedTicket> getBookedTickets() {
        ArrayList<BookedTicket> bookedTickets = new ArrayList<BookedTicket>();
        openReadableDB();
        Cursor cursor = db.query(BOOKED_TICKET_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            BookedTicket bT = new BookedTicket();
            bT.setTripId(cursor.getInt(BOOKED_TICKET_TRIP_ID_COL));
            bT.setNumAdults(cursor.getInt(BOOKED_TICKET_NUM_ADULTS_COL));
            bT.setNumKids(cursor.getInt(BOOKED_TICKET_NUM_KIDS_COL));
            bT.setDestination(cursor.getString(BOOKED_TICKET_DESTINATION_COL));
            bT.setHotel(cursor.getString(BOOKED_TICKET_HOTEL_COL));
            bT.setStartDate(cursor.getString(BOOKED_TICKET_START_DATE_COL));
            bT.setEndDate(cursor.getString(BOOKED_TICKET_END_DATE_COL));

            bookedTickets.add(bT);
        }
        closeCursor(cursor);
        closeDB();

        return bookedTickets;
    }


    // This method will return an ArrayList of UserReview objects, that will be populated
    // with data from the database!
    public ArrayList<UserReview> getUserReviews() {
        ArrayList<UserReview> userReviews = new ArrayList<UserReview>();
        openReadableDB();
        Cursor cursor = db.query(USER_REVIEW_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            UserReview uR = new UserReview();
            uR.setName(cursor.getString(USER_REVIEW_NAME_COL));
            uR.setScore(cursor.getInt(USER_REVIEW_SCORE_COL));
            uR.setReview(cursor.getString(USER_REVIEW_REVIEW_COL));

            userReviews.add(uR);
        }
        closeCursor(cursor);
        closeDB();

        return userReviews;
    }

    // SET UP ABILITY TO INSERT DATA, LIKE BOOKED_TICKET, AND USER_REVIEW
    // BUT NOT HOTEL_INFO
    public long insertBookedTicket(BookedTicket bookedTicket) {
        ContentValues cv = new ContentValues();
        cv.put(BOOKED_TICKET_NUM_ADULTS, bookedTicket.getNumAdults());
        cv.put(BOOKED_TICKET_NUM_KIDS, bookedTicket.getNumKids());
        cv.put(BOOKED_TICKET_DESTINATION, bookedTicket.getDestination());
        cv.put(BOOKED_TICKET_HOTEL, bookedTicket.getHotel());
        cv.put(BOOKED_TICKET_START_DATE, bookedTicket.getStartDate());
        cv.put(BOOKED_TICKET_END_DATE, bookedTicket.getEndDate());

        this.openWriteableDB();
        long rowID = db.insert(BOOKED_TICKET_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    // Insert User review
    public long insertUserReview(UserReview userReview) {
        ContentValues cv = new ContentValues();
        cv.put(USER_REVIEW_NAME, userReview.getName());
        cv.put(USER_REVIEW_SCORE, userReview.getScore());
        cv.put(USER_REVIEW_REVIEW, userReview.getReview());

        this.openWriteableDB();
        long rowID = db.insert(USER_REVIEW_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    // Insert User review
    public long insertHotelInfo(HotelInfo hotelInfo) {
        ContentValues cv = new ContentValues();
        cv.put(HOTEL_INFO_NAME, hotelInfo.getName());
        cv.put(HOTEL_INFO_DESCRIPTION, hotelInfo.getDescription());
        cv.put(HOTEL_INFO_IMAGE_URL, hotelInfo.getImageUrl());

        this.openWriteableDB();
        long rowID = db.insert(HOTEL_INFO_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

//    public List getList(String name) {
//        String where = LIST_NAME + "= ?";
//        String[] whereArgs = { name };
//
//        openReadableDB();
//        Cursor cursor = db.query(LIST_TABLE, null,
//                where, whereArgs, null, null, null);
//        List list = null;
//        cursor.moveToFirst();
//        list = new List(cursor.getInt(LIST_ID_COL),
//                cursor.getString(LIST_NAME_COL));
//        this.closeCursor(cursor);
//        this.closeDB();
//
//        return list;
//    }

//    public Cursor getTaskCursor(String listName) {
//        String where =
//                TASK_LIST_ID + "= ? AND " +
//                        TASK_HIDDEN + "!=1";
//        int listID = getList(listName).getId();
//        String[] whereArgs = { Integer.toString(listID) };
//
//        this.openReadableDB();
//        Cursor cursor = db.query(TASK_TABLE, null,
//                where, whereArgs,
//                null, null, null);
//        return cursor;
//    }

//    public ArrayList<Task> getTasks(String listName) {
//        String where =
//                TASK_LIST_ID + "= ? AND " +
//                        TASK_HIDDEN + "!=1";
//        int listID = getList(listName).getId();
//        String[] whereArgs = { Integer.toString(listID) };
//
//        this.openReadableDB();
//        Cursor cursor = db.query(TASK_TABLE, null,
//                where, whereArgs,
//                null, null, null);
//        ArrayList<Task> tasks = new ArrayList<Task>();
//        while (cursor.moveToNext()) {
//            tasks.add(getTaskFromCursor(cursor));
//        }
//        this.closeCursor(cursor);
//        this.closeDB();
//
//        return tasks;
//    }

//    public Task getTask(int id) {
//        String where = TASK_ID + "= ?";
//        String[] whereArgs = { Integer.toString(id) };
//
//        this.openReadableDB();
//        Cursor cursor = db.query(TASK_TABLE,
//                null, where, whereArgs, null, null, null);
//        cursor.moveToFirst();
//        Task task = getTaskFromCursor(cursor);
//        this.closeCursor(cursor);
//        this.closeDB();
//
//        return task;
//    }

//    private static Task getTaskFromCursor(Cursor cursor) {
//        if (cursor == null || cursor.getCount() == 0){
//            return null;
//        }
//        else {
//            try {
//                Task task = new Task(
//                        cursor.getInt(TASK_ID_COL),
//                        cursor.getInt(TASK_LIST_ID_COL),
//                        cursor.getString(TASK_NAME_COL),
//                        cursor.getString(TASK_NOTES_COL),
//                        cursor.getInt(TASK_COMPLETED_COL),
//                        cursor.getInt(TASK_HIDDEN_COL));
//                return task;
//            }
//            catch(Exception e) {
//                return null;
//            }
//        }
//    }



//    public int updateTask(Task task) {
//        ContentValues cv = new ContentValues();
//        cv.put(TASK_LIST_ID, task.getListId());
//        cv.put(TASK_NAME, task.getName());
//        cv.put(TASK_NOTES, task.getNotes());
//        cv.put(TASK_COMPLETED, task.getCompletedDate());
//        cv.put(TASK_HIDDEN, task.getHidden());
//
//        String where = TASK_ID + "= ?";
//        String[] whereArgs = { String.valueOf(task.getId()) };
//
//        this.openWriteableDB();
//        int rowCount = db.update(TASK_TABLE, cv, where, whereArgs);
//        this.closeDB();
//
//        return rowCount;
//    }

//    public int deleteTask(long id) {
//        String where = TASK_ID + "= ?";
//        String[] whereArgs = { String.valueOf(id) };
//
//        this.openWriteableDB();
//        int rowCount = db.delete(TASK_TABLE, where, whereArgs);
//        this.closeDB();
//
//        return rowCount;
//    }

//    public String DatabaseQuery(String operation, String dataToInput) {
//        return "";
//    }

}

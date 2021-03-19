package com.example.group5_a2;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.group5_a2.DataLayer.DatabaseManager;
import com.example.group5_a2.DataLayer.UserReview;

public class UserReviewController {
    Context context;
    DatabaseManager databaseManager;

    public UserReviewController(Context context) {
        this.context = context;
        databaseManager = new DatabaseManager(context);
    }

    public void submitReview(EditText name, RatingBar rating, EditText review) {
        UserReview userReview = new UserReview(name.getText().toString(),
                 Math.round(rating.getRating()), review.getText().toString());
        databaseManager.insertUserReview(userReview);
    }


//    // Code that later needs to be copied wherever we decide it should go

//    private Button writeReview;


//    writeReview = (Button) findViewById(R.id.write_review);



//    //Click Listener for confirmation
//        writeReview.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            // Confirm successful and back to the beginning
//            Intent intent = new Intent(MainActivity.this,
//                    UserReviewActivity.class);
//            startActivity(intent);
//        }
//    });


//    <item
//    android:id="@+id/write_review"
//    android:title="Write a Review"/>


//    <Button
//    android:id="@+id/write_review"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@+id/searchHotels_btn"
//    android:layout_marginTop="54dp"
//    android:layout_centerHorizontal="true"
//    android:text="Write a review" />

}

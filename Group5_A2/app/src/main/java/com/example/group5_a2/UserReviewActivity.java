package com.example.group5_a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserReviewActivity extends AppCompatActivity {
    private EditText name;
    private RatingBar rating;
    private EditText review;
    private Button submit;
    private Button cancel;
    private SharedPreferences savedValues;
    UserReviewController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_review_layout);

        controller = new UserReviewController(this);
        name = (EditText) findViewById(R.id.edit_name);
        rating = (RatingBar) findViewById(R.id.ratingBar_rating);
        review = (EditText) findViewById(R.id.edit_review);
        submit = (Button) findViewById(R.id.button_submit);
        cancel = (Button) findViewById(R.id.button_cancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.submitReview(name, rating, review);
                // Confirm successful and back to the beginning
                onBackPressed();
                Toast.makeText(UserReviewActivity.this,
                        "Review submitted successfully!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(UserReviewActivity.this,
//                        MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });


    }



}

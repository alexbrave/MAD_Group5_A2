package com.example.group5_a2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class ChooseHotelDetailFragment extends Fragment {

    private ImageView hotelDetailImage = null;
    private TextView hotelDetailName = null;
    private TextView hotelDetailDescription = null;
    private Button bookHotelButton = null;
    private final String TAG = "HotelDetailFragment";

    // key names for saving values when the hotel is being selected for reviewing
    private final String HOTEL_NAME_KEY = "hotel_name";
    private final String HOTEL_IMAGE_KEY = "hotel_image";
    private final String HOTEL_DESCRIPTION_KEY = "hotel_description";

    private String mHotelName = null;
    private String mHotelDescription = null;
    private String mHotelImage = null;

    /*
     *	Function: onCreate(@Nullable Bundle SavedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the TicketConfirm class.
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHotelName = getArguments().getString(HOTEL_NAME_KEY);
        mHotelDescription = getArguments().getString(HOTEL_DESCRIPTION_KEY);
        mHotelImage = getArguments().getString(HOTEL_IMAGE_KEY);
    }

    /*
     *	Function: onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the TicketConfirm class.
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.choose_hotel_detail_layout, container, false);

        hotelDetailImage = (ImageView) v.findViewById(R.id.hotel_image_detail);
        hotelDetailName = (TextView) v.findViewById(R.id.hotel_detail_name);
        hotelDetailDescription = (TextView) v.findViewById(R.id.hotel_description_detail);
        bookHotelButton = (Button) v.findViewById(R.id.hotel_book_btn);

        // display the detail of the chosen hotel
        if(!mHotelName.isEmpty() && !mHotelDescription.isEmpty() && !mHotelImage.isEmpty())
        {
            Glide.with(this).load(mHotelImage).placeholder(R.drawable.hotel1_image).into(hotelDetailImage);
            hotelDetailName.setText(mHotelName);
            hotelDetailDescription.setText(mHotelDescription);
        }

        // event handler for book hotel button
        bookHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseHotelDetailFragment.this.getContext(), TicketConfirm.class);
                intent.putExtra(HOTEL_NAME_KEY,mHotelName);
                intent.putExtra(HOTEL_IMAGE_KEY,mHotelImage);
                startActivity(intent);
                Toast.makeText(ChooseHotelDetailFragment.this.getContext(),
                        R.string.confirm_layout_label,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}

package com.example.group5_a2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HotelListAdapter extends ArrayAdapter<Hotel> {
    private String TAG = "HotelListAdapter";

    private Context mContext = null;

    int mResource = 0;

    public HotelListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Hotel> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get hotel information
        String sHotelName = getItem(position).getHotelName();
        String sHotelDescription = getItem(position).getHotelDescription();
//        String sHotelImage = getItem(position).getHotelImage();
        int iHotelRating = getItem(position).getHotelRating();
        int iHotelPrice = getItem(position).getHotelPrice();

        // Create hotel object with the information
        Hotel hHotel = new Hotel(sHotelName, sHotelDescription, "", iHotelRating, iHotelPrice);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView ivHotelImage = (ImageView) convertView.findViewById(R.id.hotel_image);
        TextView tvHotelName = (TextView) convertView.findViewById(R.id.hotel_name);
        TextView tvHotelRating = (TextView) convertView.findViewById(R.id.hotel_rating);
        TextView tvHotelPrice = (TextView) convertView.findViewById(R.id.hotel_price);

//        ivHotelImage.setImageURI(sHotelImage);
        tvHotelName.setText(sHotelName);
        tvHotelRating.setText(String.valueOf(iHotelRating));
        tvHotelPrice.setText(String.valueOf(iHotelPrice));

        return convertView;

    }
}

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
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

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
        String sHotelImage = getItem(position).getHotelImage();

        // Create hotel object with the information
        Hotel hHotel = new Hotel(sHotelName, sHotelDescription, sHotelImage);

        // inflate object to the view
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        // inflate the object's information
        ImageView ivHotelImage = (ImageView) convertView.findViewById(R.id.hotel_image);
        TextView tvHotelName = (TextView) convertView.findViewById(R.id.hotel_name);
        TextView tvHotelDescription = (TextView) convertView.findViewById(R.id.hotel_description);

        tvHotelName.setText(sHotelName);
        tvHotelDescription.setText(sHotelDescription);
        Glide.with(mContext).load(sHotelImage).placeholder(R.drawable.hotel1_image).into(ivHotelImage);

        return convertView;

    }
}

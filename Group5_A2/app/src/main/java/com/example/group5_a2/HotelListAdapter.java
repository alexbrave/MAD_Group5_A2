/*
*	PROJECT: PROG3150 - ASSIGNMENT 2
*	FILE: HotelListAdapter.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2021/03/15
*	DESCRIPTION:
		This file contains HotelListAdapter java which extends from ArrayAdapter<HotelModel>. The purpose of this class is to create adapter for HotelModel instance
*/

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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*
 *  NAME : HotelListAdapter
 *  PURPOSE : The purpose of this class is to create adapter for HotelModel instance
 */
public class HotelListAdapter extends ArrayAdapter<HotelModel> {
    private String TAG = "HotelListAdapter";

    private Context mContext = null;

    int mResource = 0;

    /*
     *	Function: Constructor - HotelListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HotelModel> objects)
     *	Description:
     *       The purpose of this function is to create an instance of the HotelListAdapter class
     *	Parameter: Context context : the context of the application
     *             int resource : the resource
     *             ArrayList<HotelModel> objects : list of hotel model objects
     *	Return: None
     */
    public HotelListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HotelModel> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    /*
     *	Function: Constructor - getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
     *	Description:
     *       The purpose of this function is to create a view of the HotelModel object for the adapter
     *	Parameter: int position : the position of the item
     *             View convertView : the view to be converted
     *             ViewGroup parent : the parent of the view
     *	Return: View: Return a HotelModel view
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get hotel information
        String sHotelName = getItem(position).getHotelName();
        String sHotelDescription = getItem(position).getHotelDescription();
        String sHotelImage = getItem(position).getHotelImage();

        // Create hotel object with the information
        HotelModel hHotel = new HotelModel(sHotelName, sHotelDescription, sHotelImage);

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

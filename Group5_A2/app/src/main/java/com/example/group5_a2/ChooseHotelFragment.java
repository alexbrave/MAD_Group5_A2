/*
*	PROJECT: PROG3150 - ASSIGNMENT 2
*	FILE: ChooseHotelFragment.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2021/03/15
*	DESCRIPTION:
		This file contains ChooseHotelFragment java. The purpose of this class is to display the interface of the list of hotels
*/

package com.example.group5_a2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.group5_a2.DataLayer.DatabaseManager;

import java.util.ArrayList;

/*
 *  NAME : ChooseHotelFragment
 *  PURPOSE : The purpose of this class is to display the interface of the list of hotels
 */
public class ChooseHotelFragment extends Fragment implements View.OnClickListener {
    Button hotel1_button = null;
    Button hotel2_button = null;
    Button hotel3_button = null;
    ListView lvHotel = null;

    private final int NONE = 0;
    private final String EMPTY = "";
    private final int hotel1 = 1;
    private final int hotel2 = 2;
    private final int hotel3 = 3;
    private final String TAG = "ChooseHotelFragment";

    // key names for saving values when the hotel is being selected for reviewing
    private final String HOTEL_NAME_KEY = "hotel_name";
    private final String HOTEL_IMAGE_KEY = "hotel_image";
    private final String HOTEL_DESCRIPTION_KEY = "hotel_description";


    // Shared Preferences values/variables
    private SharedPreferences savedValues;
    private final String sharedPrefsName = "Saved Values";

    // define instance variables
    private String destination = EMPTY;
    private int number_of_guests = NONE;
    private int chosen_hotel = NONE;


    ChooseHotelController mChooseHotelController = null;

    // Fragments
    FragmentManager fm = null;
    Fragment fragment = null;

    /*
     *	Function: onCreate(@Nullable Bundle SavedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the ChooseHotelFragment class.
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChooseHotelController = new ChooseHotelController();
        fm = getActivity().getSupportFragmentManager();
    }

    /*
     *	Function: onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
     *	Description:
     *       The purpose of this function is to display the interface of the list of hotels.
     *	Parameter: LayoutInflater inflater: the inflater
     *             ViewGroup container: the container for the interface
     *             Bundle savedInstanceState: the state of the application
     *	Return: View: Return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.choose_hotel_layout, container, false);
        lvHotel = v.findViewById(R.id.hotel_list_view);

        savedValues = getActivity().getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE);

        mChooseHotelController.LoadData(savedValues);
        destination = mChooseHotelController.getDestination();
        number_of_guests = mChooseHotelController.getNumber_of_guests();

        String fileDirectory = Environment.getExternalStorageDirectory().toString() + "/MAD/";

        // get hotel detail
        HotelModel hotel1 = new HotelModel(getString(R.string.hotel1_name),getString(R.string.hotel1_description), fileDirectory + "img1.jpg");
        HotelModel hotel2 = new HotelModel(getString(R.string.hotel2_name),getString(R.string.hotel2_description), fileDirectory + "img2.jpg");
        HotelModel hotel3 = new HotelModel(getString(R.string.hotel3_name),getString(R.string.hotel3_description), fileDirectory + "img3.jpg");
        
        DatabaseManager db = new DatabaseManager(getActivity());

        // User DatabaseManager to get hotel list
        ArrayList<HotelModel> arrHotelList = db.getHotelModels();


        // hotel list adapter
        HotelListAdapter hotelListAdapter = new HotelListAdapter(ChooseHotelFragment.this.getContext(), R.layout.hotels_adapter_view_layout, arrHotelList);
        lvHotel.setAdapter(hotelListAdapter);

        // Set event handler for each item in the list view
        lvHotel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HotelModel hotelDetails = (HotelModel) lvHotel.getItemAtPosition(position);

                fragment = new ChooseHotelDetailFragment();

                // save the values of the chosen hotel for reviewing it details
                Bundle args = new Bundle();
                args.putString(HOTEL_NAME_KEY,hotelDetails.getHotelName());
                args.putString(HOTEL_DESCRIPTION_KEY,hotelDetails.getHotelDescription());
                args.putString(HOTEL_IMAGE_KEY,hotelDetails.getHotelImage());
                fragment.setArguments(args);

                // change fragment and add to backstack
                fm.beginTransaction()
                        .replace(R.id.choose_hotels_fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

    /*
     *	Function: onClick(View v)
     *	Description:
     *       Saves data on pause
     *	Parameter: View v : View that is triggering event
     *	Return: None
     */
    @Override
    public void onClick(View v) {
        int ID = v.getId();

        // testing ticket confirm purpose
        Intent intent = null;
        intent = new Intent(ChooseHotelFragment.this.getActivity(), TicketConfirm.class);
        startActivity(intent);
        Toast.makeText(ChooseHotelFragment.this.getActivity(),
                R.string.confirm_layout_label,
                Toast.LENGTH_SHORT).show();
    }


}

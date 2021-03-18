package com.example.group5_a2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/*
 *  NAME : ChooseHotelFragment
 *  PURPOSE : The purpose of this class is to display the interface for hotel selection.
 */
public class ChooseHotelFragment extends Fragment implements View.OnClickListener {
    Button hotel1_button = null;
    Button hotel2_button = null;
    Button hotel3_button = null;

    private final int NONE = 0;
    private final String EMPTY = "";
    private final int hotel1 = 1;
    private final int hotel2 = 2;
    private final int hotel3 = 3;
    private final String TAG = "ChooseHotelFragment";

    private LinearLayout choose_hotel_options;
    private TextView choose_hotel_error;

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
     *       The purpose of this function is to create an instance of the TicketConfirm class.
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
     *	Function: onCreate(@Nullable Bundle SavedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the TicketConfirm class.
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.choose_hotel_layout, container, false);

        savedValues = getActivity().getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE);

        choose_hotel_options = (LinearLayout) v.findViewById(R.id.choose_hotel_options);
        choose_hotel_error = (TextView) v.findViewById(R.id.choose_hotel_error);

        mChooseHotelController.LoadData(savedValues);
        destination = mChooseHotelController.getDestination();
        number_of_guests = mChooseHotelController.getNumber_of_guests();

        if(destination.equals(EMPTY) || number_of_guests == NONE){
            choose_hotel_options.setVisibility(View.GONE);
            choose_hotel_error.setVisibility(View.VISIBLE);
        }
        else {
            choose_hotel_error.setVisibility(View.GONE);
            choose_hotel_options.setVisibility(View.VISIBLE);
            // Set the chosen destination to each hotel
            TextView hotel1_dest = (TextView) v.findViewById(R.id.hotel1_location);
            hotel1_dest.setText(destination);
            TextView hotel2_dest = (TextView) v.findViewById(R.id.hotel2_location);
            hotel2_dest.setText(destination);
            TextView hotel3_dest = (TextView) v.findViewById(R.id.hotel3_location);
            hotel3_dest.setText(destination);

            // Set the number of guests to the number accommodated by each hotel
            TextView hotel1_guests = (TextView) v.findViewById(R.id.hotel1_num_of_guests);
            hotel1_guests.setText(String.valueOf(number_of_guests));
            TextView hotel2_guests = (TextView) v.findViewById(R.id.hotel2_num_of_guests);
            hotel2_guests.setText(String.valueOf(number_of_guests));
            TextView hotel3_guests = (TextView) v.findViewById(R.id.hotel3_num_of_guests);
            hotel3_guests.setText(String.valueOf(number_of_guests));
        }

        // Hook up buttons to event handlers

        hotel1_button = (Button) v.findViewById(R.id.select_hotel1);
        hotel1_button.setOnClickListener(this);
        hotel2_button = (Button) v.findViewById(R.id.select_hotel2);
        hotel2_button.setOnClickListener(this);
        hotel3_button = (Button) v.findViewById(R.id.select_hotel3);
        hotel3_button.setOnClickListener(this);

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
        if(ID == R.id.select_hotel1){
            chosen_hotel = hotel1;
        }
        else if(ID == R.id.select_hotel2){
            chosen_hotel = hotel2;
        }
        else if(ID == R.id.select_hotel3){
            chosen_hotel = hotel3;
        }

        mChooseHotelController.SaveData(savedValues, chosen_hotel); // save the chosen hotel in the shared preferences

        fragment = new TicketConfirm();
        // change fragment and add to backstack
        fm.beginTransaction()
                .replace(R.id.choose_hotels_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }



}

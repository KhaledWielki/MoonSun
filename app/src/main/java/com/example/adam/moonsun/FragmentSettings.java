package com.example.adam.moonsun;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Date;

import static android.R.attr.timeZone;
import static com.example.adam.moonsun.R.layout.fragment_settings;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSettings extends Fragment implements View.OnClickListener {

    MainActivity activityMain = new MainActivity();
    Button buttonConfirm;

    EditText valueLatitude;
    EditText valueLongitude;
    EditText valueRefreshTime;

    TextView text1;
    TextView text2;
    TextView text3;

    public double latitude;
    public double longitude;
    public int refreshTime;

    public FragmentSettings() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(fragment_settings, container, false);
        buttonConfirm = (Button) myView.findViewById(R.id.button_confirm);
        buttonConfirm.setOnClickListener(this);
        valueLatitude = (EditText) myView.findViewById(R.id.value_set_latitude);
        valueLongitude = (EditText) myView.findViewById(R.id.value_set_longitude);
        valueRefreshTime = (EditText) myView.findViewById(R.id.value_set_refresh_time);

        latitude = MainActivity.latitude;
        longitude = MainActivity.longitude;
        refreshTime = MainActivity.refreshTime;

        valueLongitude.setText(Double.toString(longitude));
        valueLatitude.setText(Double.toString(latitude));
        valueRefreshTime.setText(Integer.toString(refreshTime));

        return myView;
    }

    //TODO dokończyć wyświetlanie TOASTA
    @Override
    public void onClick(View v) {
        if(((valueLatitude.getText() != null) && (valueLongitude.getText() != null) && (valueRefreshTime.getText() != null))) {
            latitude = Double.parseDouble(valueLatitude.getText().toString());
            longitude = Double.parseDouble(valueLongitude.getText().toString());
            refreshTime = Integer.parseInt(valueRefreshTime.getText().toString());

            MainActivity.latitude = latitude;
            MainActivity.longitude = longitude;
            MainActivity.refreshTime = refreshTime;

        }else {

            Toast.makeText(activityMain,"Some fields are empty!",Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.adam.moonsun;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.zip.Inflater;

import static android.R.attr.timeZone;
import static com.example.adam.moonsun.FragmentSettings.*;
import static com.example.adam.moonsun.R.layout.fragment_moon;
import static com.example.adam.moonsun.R.layout.fragment_settings;


@RequiresApi(api = Build.VERSION_CODES.N)
public class FragmentMoon extends Fragment {

    public double latitude;
    public double longitude;
    public int refreshTime;

    public double moonSetTime;
    public double moonRiseTime;
    public double closestNewMoon;
    public double closestFullMoon;
    public double moonFazePercents;
    public int synodalMonthDay;

    TextView valueMoonSetTime;
    TextView valueMoonRiseTime;
    TextView valueClosestNewMoon;
    TextView valueFullMoon;
    TextView valueMoonFaze;
    TextView valueSynodalMonthDay;


    public FragmentMoon() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(fragment_moon, container, false);
        valueMoonSetTime = (TextView) myView.findViewById(R.id.value_moon_set_time);
        valueMoonRiseTime = (TextView) myView.findViewById(R.id.value_moon_rise_time);
        valueClosestNewMoon = (TextView) myView.findViewById(R.id.value_closets_new_moon);
        valueFullMoon = (TextView) myView.findViewById(R.id.value_closets_full_moon);
        valueMoonFaze = (TextView) myView.findViewById(R.id.value_moon_percentage_faze);
        valueSynodalMonthDay = (TextView) myView.findViewById(R.id.value_day_of_synodal_month);

        latitude = MainActivity.latitude;
        longitude = MainActivity.longitude;
        refreshTime = MainActivity.refreshTime;
//TODO tutaj skończyłem, trzeba dokończyć wyświetlanie layoutów
        valueMoonSetTime.setText(Double.toString(moonSetTime));
        valueMoonRiseTime.setText(Double.toString(moonRiseTime));
        valueClosestNewMoon.setText(Integer.toString(closestNewMoon));

        return myView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        latitude = MainActivity.latitude;
        longitude = MainActivity.longitude;
        refreshTime = MainActivity.refreshTime;

        super.onCreate(savedInstanceState);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        month = month + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        int second = Calendar.getInstance().get(Calendar.SECOND);

        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        int mGMTOffset = timeZone.getRawOffset();



        AstroCalculator.Location astroLocation = new AstroCalculator.Location(latitude, longitude);
        AstroDateTime astroDateTime = new AstroDateTime(year, month, day, hour, minute, second, mGMTOffset, timeZone.inDaylightTime(new Date()));
        AstroCalculator astroCalculator = new AstroCalculator(astroDateTime, astroLocation);
        AstroCalculator.SunInfo sunInfo = astroCalculator.getSunInfo();

        

    }
}

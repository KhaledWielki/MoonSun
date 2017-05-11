package com.example.adam.moonsun;

import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.adam.moonsun.R.layout.fragment_moon;


@RequiresApi(api = Build.VERSION_CODES.N)
public class FragmentMoon extends Fragment {

    View myView;

    public double latitude;
    public double longitude;
    public int refreshTime;

    public String moonSetTime = "";
    public String moonRiseTime = "";
    public String closestNewMoon = "";
    public String closestFullMoon = "";
    public String moonFazePercents = "";
    public String synodalMonthDay = "";


    TextView valueMoonSetTime;
    TextView valueMoonRiseTime;
    TextView valueClosestNewMoon;
    TextView valueClosestFullMoon;
    TextView valueMoonFaze;
    TextView valueSynodalMonthDay;


    public FragmentMoon() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(fragment_moon, container, false);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        latitude = MainActivity.latitude;
        longitude = MainActivity.longitude;
        refreshTime = MainActivity.refreshTime;

        valueMoonSetTime = (TextView) myView.findViewById(R.id.value_moon_set_time);
        valueMoonRiseTime = (TextView) myView.findViewById(R.id.value_moon_rise_time);
        valueClosestNewMoon = (TextView) myView.findViewById(R.id.value_closets_new_moon);
        valueClosestFullMoon = (TextView) myView.findViewById(R.id.value_closets_full_moon);
        valueMoonFaze = (TextView) myView.findViewById(R.id.value_moon_percentage_faze);
        valueSynodalMonthDay = (TextView) myView.findViewById(R.id.value_day_of_synodal_month);


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
        AstroCalculator.MoonInfo moonInfo = astroCalculator.getMoonInfo();

        moonFazePercents += decimalFormat.format((1 - moonInfo.getIllumination()) * 100) +"%";

        if(refreshTime != 0) {
            Timer myTimer = new Timer();
            myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    refresh();
                }
            }, 0, (refreshTime * 60 * 1000));
        }

        if(moonInfo.getMoonset().getHour()+1 < 10 || moonInfo.getMoonset().getMinute() < 10) {
            if(moonInfo.getMoonset().getHour()+1 < 10 && moonInfo.getMoonset().getMinute() < 10) {
                moonSetTime += "0"+(moonInfo.getMoonset().getHour()+1) + ":" + "0"+moonInfo.getMoonset().getMinute();
            }
            else if(moonInfo.getMoonset().getHour()+1 < 10) {
                moonSetTime += "0" + (moonInfo.getMoonset().getHour()+1) + ":" + moonInfo.getMoonset().getMinute();
            }
            else
                moonSetTime += (moonInfo.getMoonset().getHour()+1) + ":" + "0" + moonInfo.getMoonset().getMinute();
        } else
            moonSetTime += (moonInfo.getMoonset().getHour()+1) + ":" + moonInfo.getMoonset().getMinute();

        if(moonInfo.getMoonrise().getHour()+1 < 10 || moonInfo.getMoonrise().getMinute() < 10) {
            if(moonInfo.getMoonrise().getHour()+1 < 10 && moonInfo.getMoonrise().getMinute() < 10) {
                moonRiseTime += "0"+(moonInfo.getMoonrise().getHour()+1) + ":" + "0"+moonInfo.getMoonrise().getMinute();
            }
            else if(moonInfo.getMoonrise().getHour()+1 < 10) {
                moonRiseTime += "0" + (moonInfo.getMoonrise().getHour()+1) + ":" + moonInfo.getMoonrise().getMinute();
            }
            else
                moonRiseTime += (moonInfo.getMoonrise().getHour()+1) + ":" + "0" + moonInfo.getMoonrise().getMinute();
        }
        else
            moonRiseTime += (moonInfo.getMoonrise().getHour()+1) + ":" + moonInfo.getMoonrise().getMinute();

        int temp = moonInfo.getNextFullMoon().getMonth();
        switch (temp) {
            case 1:
            if(moonInfo.getNextFullMoon().getDay() < 10) {
                closestFullMoon += "January " + "0" +moonInfo.getNextFullMoon().getDay();
            } else
                closestFullMoon += "January " +moonInfo.getNextFullMoon().getDay();
                break;

            case 2:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "February " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "February " +moonInfo.getNextFullMoon().getDay();
                break;

            case 3:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "March " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "March " +moonInfo.getNextFullMoon().getDay();
                break;

            case 4:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "April " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "April " +moonInfo.getNextFullMoon().getDay();
                break;

            case 5:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "May " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "May " +moonInfo.getNextFullMoon().getDay();
                break;

            case 6:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "June " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "June " +moonInfo.getNextFullMoon().getDay();
                break;

            case 7:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "July " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "July " +moonInfo.getNextFullMoon().getDay();
                break;

            case 8:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "August " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "August " +moonInfo.getNextFullMoon().getDay();
                break;

            case 9:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "September " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "September " +moonInfo.getNextFullMoon().getDay();
                break;

            case 10:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "October " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "October " +moonInfo.getNextFullMoon().getDay();
                break;

            case 11:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "November " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "November " +moonInfo.getNextFullMoon().getDay();
                break;
            case 12:
                if(moonInfo.getNextFullMoon().getDay() < 10) {
                    closestFullMoon += "December " + "0" +moonInfo.getNextFullMoon().getDay();
                } else
                    closestFullMoon += "December " +moonInfo.getNextFullMoon().getDay();
                break;
        }

        int temp2 = moonInfo.getNextNewMoon().getMonth();
        switch (temp2) {
            case 1:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "January " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "January " +moonInfo.getNextNewMoon().getDay();
                break;

            case 2:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "February " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "February " +moonInfo.getNextNewMoon().getDay();
                break;

            case 3:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "March " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "March " +moonInfo.getNextNewMoon().getDay();
                break;

            case 4:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "April " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "April " +moonInfo.getNextNewMoon().getDay();
                break;

            case 5:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "May " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "May " +moonInfo.getNextNewMoon().getDay();
                break;

            case 6:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "June " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "June " +moonInfo.getNextNewMoon().getDay();
                break;

            case 7:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "July " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "July " +moonInfo.getNextNewMoon().getDay();
                break;

            case 8:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "August " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "August " +moonInfo.getNextNewMoon().getDay();
                break;

            case 9:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "September " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "September " +moonInfo.getNextNewMoon().getDay();
                break;

            case 10:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "October " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "October " +moonInfo.getNextNewMoon().getDay();
                break;

            case 11:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "November " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "November " +moonInfo.getNextNewMoon().getDay();
                break;
            case 12:
                if(moonInfo.getNextNewMoon().getDay() < 10) {
                    closestNewMoon += "December " + "0" +moonInfo.getNextNewMoon().getDay();
                } else
                    closestNewMoon += "December " +moonInfo.getNextNewMoon().getDay();
                break;
        }

        valueMoonSetTime.setText(moonSetTime);
        valueMoonRiseTime.setText(moonRiseTime);
        valueClosestFullMoon.setText(closestFullMoon);
        valueClosestNewMoon.setText(closestNewMoon);
        valueMoonFaze.setText(moonFazePercents);

        return myView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void refresh() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        latitude = MainActivity.latitude;
        longitude = MainActivity.longitude;
        refreshTime = MainActivity.refreshTime;

        valueMoonFaze = (TextView) myView.findViewById(R.id.value_moon_percentage_faze);

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
        AstroCalculator.MoonInfo moonInfo = astroCalculator.getMoonInfo();

        moonFazePercents = decimalFormat.format((1 - moonInfo.getIllumination()) * 100) +"%";

        valueMoonFaze.setText(moonFazePercents);
    }
}

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

import static com.example.adam.moonsun.R.layout.fragment_moon;
import static com.example.adam.moonsun.R.layout.fragment_sun;


public class FragmentSun extends Fragment {

    public double latitude;
    public double longitude;
    public int refreshTime;

    public String sunRiseTimeWithAzimuth = "";
    public String sunSetTimeWithAzimuth = "";
    public String sunRiseCivilTime = "";
    public String sunSetCivilTime = "";

    String month = "";
    String day = "";

    TextView valueSunRise;
    TextView valueSunSet;
    TextView valueSunRiseCivil;
    TextView valueSunSetCivil;

    public FragmentSun() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(fragment_sun, container, false);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        latitude = MainActivity.latitude;
        longitude = MainActivity.longitude;
        refreshTime = MainActivity.refreshTime;

        valueSunRise = (TextView) myView.findViewById(R.id.value_sun_rise_time);
        valueSunSet = (TextView) myView.findViewById(R.id.value_sun_set_time);
        valueSunRiseCivil = (TextView) myView.findViewById(R.id.value_sun_rise_civil_time);
        valueSunSetCivil = (TextView) myView.findViewById(R.id.value_sun_set_civil_time);


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

        if (sunInfo.getSunset().getHour()+1 < 10 || sunInfo.getSunset().getMinute() < 10) {
            if (sunInfo.getSunset().getHour()+1 < 10 && sunInfo.getSunset().getMinute() < 10) {
                sunSetTimeWithAzimuth += "0" + (sunInfo.getSunset().getHour()+1) + ":" + "0" + sunInfo.getSunset().getMinute() + "   " + decimalFormat.format(sunInfo.getAzimuthSet());
            } else if (sunInfo.getSunset().getHour()+1 < 10) {
                sunSetTimeWithAzimuth += "0" + (sunInfo.getSunset().getHour()+1) + ":" + sunInfo.getSunset().getMinute() + "   " + decimalFormat.format(sunInfo.getAzimuthSet());
            } else
                sunSetTimeWithAzimuth += (sunInfo.getSunset().getHour()+1) + ":" + "0" + sunInfo.getSunset().getMinute() + "   " + decimalFormat.format(sunInfo.getAzimuthSet());
        } else
            sunSetTimeWithAzimuth += (sunInfo.getSunset().getHour()+1) + ":"  + sunInfo.getSunset().getMinute() + "   " + decimalFormat.format(sunInfo.getAzimuthSet());

        if (sunInfo.getSunrise().getHour()+1 < 10 || sunInfo.getSunrise().getMinute() < 10) {
            if (sunInfo.getSunrise().getHour()+1 < 10 && sunInfo.getSunrise().getMinute() < 10) {
                sunRiseTimeWithAzimuth += "0" + (sunInfo.getSunrise().getHour()+1) + ":" + "0" + sunInfo.getSunrise().getMinute() + "   " + decimalFormat.format(sunInfo.getAzimuthRise());
            } else if (sunInfo.getSunrise().getHour()+1 < 10) {
                sunRiseTimeWithAzimuth += "0" + (sunInfo.getSunrise().getHour()+1) + ":" + sunInfo.getSunrise().getMinute() + "   " + decimalFormat.format(sunInfo.getAzimuthRise());
            } else
                sunRiseTimeWithAzimuth += (sunInfo.getSunrise().getHour()+1) + ":" + "0" + sunInfo.getSunrise().getMinute() + "   " + decimalFormat.format(sunInfo.getAzimuthRise());
        } else
            sunRiseTimeWithAzimuth += (sunInfo.getSunrise().getHour()+1) + ":" + sunInfo.getSunrise().getMinute() + "   " + decimalFormat.format(sunInfo.getAzimuthRise());


        valueSunRise.setText(sunRiseTimeWithAzimuth);
        valueSunSet.setText(sunSetTimeWithAzimuth);


        return myView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

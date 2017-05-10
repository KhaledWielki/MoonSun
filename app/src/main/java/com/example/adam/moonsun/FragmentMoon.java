package com.example.adam.moonsun;

import android.icu.util.Calendar;
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

import java.util.Date;
import java.util.zip.Inflater;

import static android.R.attr.timeZone;
import static com.example.adam.moonsun.FragmentSettings.*;


public class FragmentMoon extends Fragment {

    double latitude = FragmentSettings.

    public FragmentMoon() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moon, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        month = month + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        int second = Calendar.getInstance().get(Calendar.SECOND);

        AstroCalculator.Location astroLocation = new AstroCalculator.Location(FragmentSettings., FragmentSettings.longitude);
        AstroDateTime astroDateTime = new AstroDateTime(year, month, day, hour, minute, second, getGMTOffset(timeZone), timeZone.inDaylightTime(new Date()));
        AstroCalculator astroCalculator = new AstroCalculator(astroDateTime, astroLocation);
        AstroCalculator.SunInfo sunInfo = astroCalculator.getSunInfo();

    }
}

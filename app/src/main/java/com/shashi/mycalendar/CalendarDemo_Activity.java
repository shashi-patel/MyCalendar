package com.shashi.mycalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.shashikant.calendar.SNPCalendarView;
import com.shashikant.calendar.onSNPCalendarViewListener;

import java.util.ArrayList;

/**
 * Created by shashi on 20/3/18.
 */

public class CalendarDemo_Activity extends AppCompatActivity {

    SNPCalendarView snpcv_demo;
    String TAG = getClass().getSimpleName();
    ArrayList<String> eventDays = new ArrayList<String>();
    ArrayList<String> eventDays1 = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calendardemo);

        snpcv_demo = (SNPCalendarView) findViewById(R.id.snpcv_demo);

        set_calendar_events();

        snpcv_demo.setOnCalendarViewListener(new onSNPCalendarViewListener() {
            @Override
            public void onDateChanged(String date) {
                Log.d(TAG, "date->" + date);
                set_calendar_events();
                Toast.makeText(CalendarDemo_Activity.this, date, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDisplayedMonthChanged(int month, int year, String monthStr) {
                Log.d(TAG, "month->" + month);
                Log.d(TAG, "year->" + year);
                Log.d(TAG, "monthStr->" + monthStr);
            }
        });
    }

    public void set_calendar_events() {
        eventDays.clear();
        eventDays.add("2018-03-23");
        eventDays.add("2018-03-24");
        eventDays.add("2018-03-25");
        eventDays.add("2018-03-26");
        eventDays.add("2018-03-27");

        eventDays1.clear();
        eventDays1.add("2018-03-10");
        eventDays1.add("2018-03-11");
        eventDays1.add("2018-03-12");
        eventDays1.add("2018-03-13");
        eventDays1.add("2018-03-14");

        snpcv_demo.setEvents(eventDays);
        snpcv_demo.setEvents1(eventDays1);
    }
}

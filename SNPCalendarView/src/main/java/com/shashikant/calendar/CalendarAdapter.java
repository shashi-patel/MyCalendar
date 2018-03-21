package com.shashikant.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.shashikant.snpcalendar.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by shashi on 6/3/18.
 */

public class CalendarAdapter extends BaseAdapter {

    private Context mContext;

    private Calendar month;
    public GregorianCalendar pmonth;
    //calendar instance for previous month for getting complete view
    public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    private int firstDay;
    private int maxWeeknumber;
    private int maxP;
    private int calMaxP;
    private int mnthlength;
    private String itemvalue, curentDateString;
    DateFormat df;

    private ArrayList<String> items;
    private ArrayList<String> items1;
    private ArrayList<String> items2;
    public static List<String> dayString;
    private View previousView;

    String TAG = getClass().getSimpleName();

    public CalendarAdapter(Context context, GregorianCalendar monthCalendar) {
        mContext = context;
        initCalendarAdapter(monthCalendar, null);
    }

    public void initCalendarAdapter(GregorianCalendar monthCalendar,
                                    onSNPCalendarViewListener calendarListener) {
        CalendarAdapter.dayString = new ArrayList<String>();
        month = monthCalendar;

        selectedDate = (GregorianCalendar) monthCalendar.clone();

        month.set(GregorianCalendar.DAY_OF_MONTH, 1);
        this.items = new ArrayList<String>();
        this.items1 = new ArrayList<String>();
        this.items2 = new ArrayList<String>();

        adaptersetDate(selectedDate, calendarListener);
        refreshDays();
    }

    public void setItems(ArrayList<String> items) {
        if (items == null)
            return;
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items = items;
    }

    public void setItems1(ArrayList<String> items) {
        if (items == null)
            return;

        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items1 = items;
    }

    public void setItems2(ArrayList<String> items) {

        if (items == null)
            return;

        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items2 = items;
    }

    public int getCount() {
        return dayString.size();
    }

    public Object getItem(int position) {
        return dayString.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView tv_date;
        ImageView iv_event1, iv_event2, iv_event3;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.calendar_item, null);
        }
        tv_date = (TextView) v.findViewById(R.id.tv_date);
        iv_event1 = (ImageView) v.findViewById(R.id.date_icon);
        iv_event2 = (ImageView) v.findViewById(R.id.date_icon1);
        iv_event3 = (ImageView) v.findViewById(R.id.date_icon2);

        String[] separatedTime = dayString.get(position).split("-"); //split date string using -
        String gridvalue = separatedTime[2].replaceFirst("^0*", "");

        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            tv_date.setTextColor(Color.GRAY);
            tv_date.setClickable(false);
            tv_date.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 15) && (position > 28)) {
            tv_date.setTextColor(Color.GRAY);
            tv_date.setClickable(false);
            tv_date.setFocusable(false);
        } else {
            //current month
            tv_date.setTextColor(v.getResources().getColor(R.color.white));
        }
        if (dayString.get(position).equals(curentDateString)) {
            setSelected(v);
            previousView = v;
        } else {
            v.setBackgroundResource(R.drawable.list_item_background);
        }
        tv_date.setText(gridvalue);

        String date = dayString.get(position);

        if (date.length() == 1) {
            date = "0" + date;
        }
        Log.d(TAG, "view_height->" + v.getHeight());
        //show icon if event is present

        if (date.length() > 0 && items != null && items.contains(date)) {
            iv_event1.setVisibility(View.VISIBLE);
        } else {
            iv_event1.setVisibility(View.INVISIBLE);
        }

        if (date.length() > 0 && items1 != null && items1.contains(date)) {
            iv_event2.setVisibility(View.VISIBLE);
            iv_event1.setVisibility(View.INVISIBLE);
        } else {
            iv_event2.setVisibility(View.INVISIBLE);
        }

        if (date.length() > 0 && items2 != null && items2.contains(date)) {
            iv_event3.setVisibility(View.VISIBLE);
        } else {
            iv_event3.setVisibility(View.INVISIBLE);
        }
        return v;
    }

    public View setSelected(View view) {
        if (previousView != null) {
            previousView.setBackgroundResource(R.drawable.list_item_background);
        }
        previousView = view;
        view.setBackgroundResource(R.color.background);
        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_date.setTextColor(mContext.getResources().getColor(R.color.blue));
        return view;
    }

    public void refreshDays() {
        items.clear();
        dayString.clear();

        pmonth = (GregorianCalendar) month.clone();
        firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);//start day
        maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH); // weeks
        mnthlength = maxWeeknumber * 7;
        maxP = getMaxP();
        calMaxP = maxP - (firstDay - 1);
        /**
         * Calendar instance for populating complete gridview including the three
         * month's (previous,current,next) dates.
         */
        pmonthmaxset = (GregorianCalendar) pmonth.clone();
        /**
         * set the start date as previous month's required date.
         */
        pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

        /**
         * filling calendar gridview.
         */
        for (int n = 0; n < mnthlength; n++) {
            itemvalue = df.format(pmonthmaxset.getTime());
            pmonthmaxset.add(GregorianCalendar.DATE, 1);
            dayString.add(itemvalue);
        }
    }

    private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }

    public void adaptersetDate(GregorianCalendar monthCalendar, onSNPCalendarViewListener c) {
        df = new SimpleDateFormat("yyyy-MM-dd", Util.getLocale());
        selectedDate = monthCalendar;
        curentDateString = df.format(selectedDate.getTime());
    }
}
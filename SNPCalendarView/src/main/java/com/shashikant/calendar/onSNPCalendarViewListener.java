package com.shashikant.calendar;


/**
 * Created by shashi on 6/3/18.
 */

public interface onSNPCalendarViewListener {

	void onDateChanged(String date);

	/**
	 * @param month first month is: 1 (January) and last month is: 12 (Dec)
	 * @param year
	 * @param monthStr provides local month name like January (English) or Ocak (Turkish)
	 * */
	void onDisplayedMonthChanged(int month, int year, String monthStr);
	
}

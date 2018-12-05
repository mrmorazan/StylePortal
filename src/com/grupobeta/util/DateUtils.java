package com.grupobeta.util;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
	private DateUtils() {}

	public final static long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
	public final static long MINUTES_IN_DAY = 60 * 1000;

	public static int DayOfWeek() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static int DayOfWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static int DifferenceInDays(Date from, Date to) {
		return (int)((to.getTime() - from.getTime()) / MILLISECONDS_IN_DAY);
	}

	public static int DifferenceInHours(Date from, Date to) {
		return (int)((to.getTime() - from.getTime()) / MILLISECONDS_IN_DAY)*24;
	}

	public static long DifferenceInMinutes(Date from, Date to) {
		return ((to.getTime() - from.getTime()) / MINUTES_IN_DAY);
	}


	public static Date FromDouble(double value) {
		Date d = new Date((long)value);
		return d;
	}

	public static Date AddHours(Date d, int hours) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.HOUR_OF_DAY, hours);
		return c.getTime();
	}

	public static Date SubstractHours(Date d, int hours) {
		return AddHours(d, -hours);
	}

	public static Date AddDays(int days) {
		return AddDays(Today(), days);
	}

	public static Date AddDays(Date d, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	public static Date SubstractDays(Date d, int days) {
		return AddDays(d, -days);
	}

	public static Date AddDay(Date d) {
		return AddDays(d, 1);
	}

	public static Date SubstractDay(Date d) {
		return AddDays(d, -1);
	}

	public static Date Truncate(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date Now() {
		return new Date();
	}

	public static Date Today() {
		return Truncate(new Date());
	}

	public static Date Tomorrow() {
		return AddDay(Today());
	}

	public static Date Yesterday() {
		return SubstractDay(Today());
	}

	public static short Year(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return (short) c.get(Calendar.YEAR);
	}

	public static short Year() {
		return Year(Today());
	}

	public static Date FirstDayOfWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return c.getTime();
	}

	public static Date FirstDayOfWeek() {
		return FirstDayOfWeek(Today());
	}

	public static Date LastDayOfWeek(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.add(Calendar.WEEK_OF_YEAR, 1);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	public static Date LastDayOfWeek() {
		return LastDayOfWeek(Today());
	}

	public static Date FirstDayOfMonth(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static Date FirstDayOfMonth() {
		return FirstDayOfMonth(Today());
	}

	public static Date LastDayOfMonth(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	public static Date LastDayOfMonth() {
		return LastDayOfMonth(Today());
	}

	public static Date FirstDayOfYear(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static Date FirstDayOfYear() {
		return FirstDayOfYear(Today());
	}

	public static Date LastDayOfYear(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.YEAR, 1);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	public static Date LastDayOfYear() {
		return LastDayOfYear(Today());
	}

	public static int HourOfDay() {
		return HourOfDay(Now());
	}

	public static int HourOfDay(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static int Compare(Date d1, Date d2) {
		if (d1 == null)
			return d2 == null ? 0 : -1;
		else
			return d2 == null ? 1 : d1.compareTo(d2);
	}

	public static Date Max(Date d1, Date d2) {
		return Compare(d1, d2) > 0 ? d1 : d2;
	}

	public static Date Max(Date d1, Date... dates) {
		Date max = d1;
		for(Date d: dates)
			if (Compare(d, max) > 0)
				max = d;

		return max;
	}




	public static boolean Between(Date d1, Date d2, Date date) {
		return d1.compareTo(date) <= 0 && date.compareTo(d2) <= 0;
	}
}


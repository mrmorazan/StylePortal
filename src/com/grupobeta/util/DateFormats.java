package com.grupobeta.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormats {
	private DateFormats() { }
	
	public final static String SHORT_DATE_FORMAT = "yyyy/MM/dd";	
	public final static String SHORT_TIME_FORMAT = "hh:mm:ss a";
	public final static String SHORT_DATETIME_FORMAT = SHORT_DATE_FORMAT + " " + SHORT_TIME_FORMAT;

	public final static String REPORT_DATE_FORMAT = "dd/MM/yyyy";
	public final static String REPORT_TIME_FORMAT = "hh:mm a";
	public final static String REPORT_DATETIME_FORMAT = REPORT_DATE_FORMAT + " " + REPORT_TIME_FORMAT;

	public final static String US_DATE_FORMAT = "MM/dd/yyyy";
	public final static String US_TIME_FORMAT = "h:mm a";
	public final static String US_DATETIME_FORMAT = US_DATE_FORMAT + " " + US_TIME_FORMAT;
	
	public final static String MILITARY_TIME_FORMAT = "HH:mm";
	public static DateFormat MilitaryTimeFormatter = new SimpleDateFormat(MILITARY_TIME_FORMAT);
	
	public static DateFormat ShortDateFormatter = new SimpleDateFormat(SHORT_DATE_FORMAT);
	public static DateFormat ShortTimeFormatter = new SimpleDateFormat(SHORT_TIME_FORMAT);
	public static DateFormat ShortDateTimeFormatter = new SimpleDateFormat(SHORT_DATETIME_FORMAT);

	public static DateFormat ReportDateFormatter = new SimpleDateFormat(REPORT_DATE_FORMAT);
	public static DateFormat ReportTimeFormatter = new SimpleDateFormat(REPORT_TIME_FORMAT);
	public static DateFormat ReportDateTimeFormatter = new SimpleDateFormat(REPORT_DATETIME_FORMAT);
	
	public static String ShortDateFormat(Date d) {
		return ShortDateFormatter.format(d);
	}
	
	public static Date ShortDateParse(String s) throws ParseException {
		return ShortDateFormatter.parse(s);
	}
	
	public static String ShortTimeFormat(Date d) {
		return ShortTimeFormatter.format(d);
	}
	
	public static Date ShortTimeParse(String s) throws ParseException {
		return ShortTimeFormatter.parse(s);
	}
	
	public static String MilitaryTimeFormat(Date d) {
		return MilitaryTimeFormatter.format(d);
	}
	
	public static Date MilitaryTimeParse(String s) throws ParseException {
		return MilitaryTimeFormatter.parse(s);
	}
	
	public static String ShortDateTimeFormat(Date d) {
		return ShortDateTimeFormatter.format(d);
	}
	
	public static Date ShortDateTimeParse(String s) throws ParseException {
		return ShortDateTimeFormatter.parse(s);
	}
	
	public static String ReportDateFormat(Date d) {
		return ReportDateFormatter.format(d);
	}
	
	public static Date ReportDateParse(String s) throws ParseException {
		return ReportDateFormatter.parse(s);
	}
	
	public static String ReportTimeFormat(Date d) {
		return ReportTimeFormatter.format(d);
	}
	
	public static Date ReportTimeParse(String s) throws ParseException {
		return ReportTimeFormatter.parse(s);
	}
	
	public static String ReportDateTimeFormat(Date d) {
		return ReportDateTimeFormatter.format(d);
	}
	
	public static Date ReportDateTimeParse(String s) throws ParseException {
		return ReportDateTimeFormatter.parse(s);
	}
	
	public static SimpleDateFormat GetFormatter(String format) {
		return new SimpleDateFormat(format);
	}
}

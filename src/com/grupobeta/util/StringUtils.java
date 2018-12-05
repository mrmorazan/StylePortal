package com.grupobeta.util;

public class StringUtils {
	private StringUtils() {}
	
	public static boolean IsNotNullNorEmpty(String s) {
		return s != null && !s.trim().isEmpty();
	}
	
	public static boolean IsNullOrEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}
	
	public static boolean IsNullOrEmpty(String... s) {
		for(String ss: s) {
			if (!IsNullOrEmpty(ss)) {
				return false;
			}
		}
		return true;
	}
	
	public static String padLeft(String s, int length, char c) {
		StringBuilder sb = new StringBuilder(s);
		length -= s.length();
		while (length > 0) {
			sb.insert(0, c);
			length--;
		}
		return sb.toString();
	}
	
	public static String padRight(String s, int length, char c) {
		StringBuilder sb = new StringBuilder(s);
		length -= s.length();
		while (length > 0) {
			sb.append(c);
			length--;
		}
		return sb.toString();
	}
	
	public static String trimLeft(String s, String trim) {
		if(s.startsWith(trim))
			return s.substring(trim.length());
		else
			return s;
	}
	
	public static String trimRight(String s, String trim) {
		if(s.endsWith(trim))
			return s.substring(0, s.length() - trim.length());
		else
			return s;
	}
}

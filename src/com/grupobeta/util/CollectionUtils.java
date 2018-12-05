package com.grupobeta.util;

import java.util.HashSet;
import java.util.Set;

public final class CollectionUtils {
	private CollectionUtils() { }

	public static <T> Set<T> SetOf(T value) {
		Set<T> set = new HashSet<T>();
		set.add(value);
		return set;
	}

	public static <T> Set<T> SetOf(T value1, T value2) {
		Set<T> set = new HashSet<T>();
		set.add(value1);
		set.add(value2);
		return set;
	}

	public static <T> Set<T> SetOf(T value1, T value2, T value3) {
		Set<T> set = new HashSet<T>();
		set.add(value1);
		set.add(value2);
		set.add(value3);
		return set;
	}

	@SuppressWarnings("unchecked")
	public static <T> Set<T> SetOf(T... values) {
		Set<T> set = new HashSet<T>();
		for (T value: values)
			set.add(value);
		return set;
	}

	public static <T> Set<T> SetFrom(T[] values) {
		Set<T> set = new HashSet<T>();
		for (T value: values)
			set.add(value);
		return set;
	}
}

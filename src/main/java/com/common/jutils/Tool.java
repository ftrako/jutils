package com.common.jutils;

public class Tool {
	public static boolean isEmpty(String str) {
		return str == null || str.length() <= 0;
	}

	public static <T>  boolean isEmpty(T t) {
		return t == null;
	}
}

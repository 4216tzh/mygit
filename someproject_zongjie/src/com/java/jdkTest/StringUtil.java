package com.java.jdkTest;

import java.util.UUID;

public class StringUtil {
	
	
	public static String getRandom() {
		Double d=Math.random()*10000000;
		Integer i=d.intValue();
		return String.valueOf(i);
	}
	
	public static String getUUID() {
		String str=UUID.randomUUID().toString();
		return str.replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getRandom());
		System.out.println(getUUID());
	}

}

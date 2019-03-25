package com.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
		//new Timer().s
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Œ“ «À≠:"+new Date().getTime());
			}
		},3000,3000);
	test();
	}
	
	private static String test() {
		Integer i=0;
		System.out.println(i++);
		String name="jack";
		String result="mike".equals(name)?name:"";
		List<String> list=new ArrayList<String>();
		list.add(result);
		return null;
	}

}

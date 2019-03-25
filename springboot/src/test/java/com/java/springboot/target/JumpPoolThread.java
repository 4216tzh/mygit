package com.java.springboot.target;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JumpPoolThread implements Runnable{
	
	private String name;
	
	public JumpPoolThread(String name){
		this.name=name;
	}

	@Override
	public void run() {
		System.out.println("当前时间：："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())+","+Thread.currentThread().getName()+","+name);
	}

}

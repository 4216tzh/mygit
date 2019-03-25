package com.java.springboot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WxToken {
	
	//@Scheduled(fixedDelay=10000)
	public void getToken(){
		System.out.println("这是一次实战演练!!!");
	}

}

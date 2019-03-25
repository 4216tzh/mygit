package com.java.springboot.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ListenerEventService {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	public void register(String message){
		//一些业务逻辑
		/**
		 * 发布监听事件
		 */
		applicationContext.publishEvent(new IsListenerEvent(this,message));
	}

}

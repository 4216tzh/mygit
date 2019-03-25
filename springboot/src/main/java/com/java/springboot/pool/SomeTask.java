package com.java.springboot.pool;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SomeTask {
	
	@Async
	public void index(){
		LoggerFactory.getLogger(SomeTask.class).info(":Hello World!");
	}

}

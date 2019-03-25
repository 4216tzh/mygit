package com.java.springboot.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
/**
 * 监听一个事件
 * @author tzh
 *
 */
@Component
public class EventListeners {
	
	@EventListener
	//@Async
	public void event(IsListenerEvent obj){
		/* try {
	            Thread.sleep(3000);//静静的沉睡3秒钟
	        }catch (Exception e)
	        {
	            e.printStackTrace();
	        }*/
		System.out.println("监听到的事件>>>>>"+obj.toString());
	}

}

package com.java.springboot.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义一个事件
 * @author tzh
 *
 */
public class IsListenerEvent extends ApplicationEvent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public IsListenerEvent(Object source,String message) {
		super(source);
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "IsListenerEvent [message=" + message + "]";
	}

	
	
}

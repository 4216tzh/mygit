package com.java.design.observer.Impl;

import com.java.design.observer.Observer;

public class ObserverImpl implements Observer {
	
	//推送消息
	private String message;
	//观察者名称(订阅者)
	private String name;

	@Override
	public void update(String message) {
		this.message=message;
		System.out.println(name+"特工收到消息:"+this.message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ObserverImpl(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

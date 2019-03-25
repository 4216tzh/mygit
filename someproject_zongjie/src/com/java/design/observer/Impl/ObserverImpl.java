package com.java.design.observer.Impl;

import com.java.design.observer.Observer;

public class ObserverImpl implements Observer {
	
	//������Ϣ
	private String message;
	//�۲�������(������)
	private String name;

	@Override
	public void update(String message) {
		this.message=message;
		System.out.println(name+"�ع��յ���Ϣ:"+this.message);
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

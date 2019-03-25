package com.java.design.observer.Impl;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.java.design.observer.Observer;
import com.java.design.observer.Subject;

public class SubjectImpl implements Subject{
	
	private BlockingQueue<Observer > queue=null;
	
	private String message;
	
	public SubjectImpl(String message){
		queue=new LinkedBlockingQueue<Observer>();
		this.message=message;
	}
	
	public SubjectImpl(){
		queue=new LinkedBlockingQueue<Observer>();
	}

	@Override
	public void addObserver(Observer o) {
		if(o!=null){
			queue.add(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		if(o!=null){
			queue.remove(o);
		}
	}

	@Override
	public void notifyObserver() {
		Iterator<Observer> i=queue.iterator();
		while(i.hasNext()){
			Observer s=i.next();
			s.update(message);
		}
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
		notifyObserver();
	}
	

}

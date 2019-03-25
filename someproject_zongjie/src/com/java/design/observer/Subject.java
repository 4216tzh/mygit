package com.java.design.observer;


/**
 *  被观察者
 * @author tzh
 * 声明了添加、删除、通知观察者方法
 */
public interface Subject {
	
	public void addObserver(Observer o);
	
	public void removeObserver(Observer o);
	
	public void notifyObserver();
}

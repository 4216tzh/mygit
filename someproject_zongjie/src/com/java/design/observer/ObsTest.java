package com.java.design.observer;

import com.java.design.observer.Impl.ObserverImpl;
import com.java.design.observer.Impl.SubjectImpl;

/**
 * �۲���ģʽ����
 * @author tzh
 *
 */
public class ObsTest {
	
	public static void main(String[] args) {
		Observer o1=new ObserverImpl("tom");
		Observer o2=new ObserverImpl("bob");
		Observer o3=new ObserverImpl("lily");
		Observer o4=new ObserverImpl("jack");
		Observer o5=new ObserverImpl("mike");
		Subject s=new SubjectImpl("�����ˣ����·��ˣ�");
		s.addObserver(o5);
		s.addObserver(o4);
		s.addObserver(o3);
		s.addObserver(o2);
		s.addObserver(o1);
		s.notifyObserver();
		
	}

}

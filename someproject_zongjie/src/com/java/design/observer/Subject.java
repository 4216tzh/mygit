package com.java.design.observer;


/**
 *  ���۲���
 * @author tzh
 * ��������ӡ�ɾ����֪ͨ�۲��߷���
 */
public interface Subject {
	
	public void addObserver(Observer o);
	
	public void removeObserver(Observer o);
	
	public void notifyObserver();
}

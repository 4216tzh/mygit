package com.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainTest {
	
	public static  ReentrantLock Lock=new ReentrantLock();
	
	public static  Condition Full=Lock.newCondition();
	
	public static  Condition Empty=Lock.newCondition();
	
	
	public static void main(String[] args) {
		List<String> queue=new ArrayList<String>();
		Thread t=new Thread(new Productor(queue));
		Thread t1=new Thread(new Consume(queue));
		t1.start();
		t.start();
		
		
		
		
		/*@Query(value = "SELECT new com.johnfnash.learn.domain.ViewInfo(u, a) FROM UserInfo u, Address a WHERE u.addressId = a.addressId")
		public List<ViewInfo> findViewInfo();*/
		
	}

}

package com.java.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestMain {
	
	public static ReentrantLock lock=new ReentrantLock();
	
	public static Condition full=lock.newCondition();
	
	public static Condition empty=lock.newCondition();
	
	static List<String>  queue=new ArrayList<String>();
	
	public static void main(String[] args) {
		
		Thread t1=new Thread(new Productor(queue));
		Thread t=new Thread(new UseMoney(queue));
		t.start();
		t1.start();
		
				
		/*
		List<String> list=new ArrayList<String>();
		Productor q=new Productor(list);
		Productor q1=new Productor(list);
		Productor q2=new Productor(list);
		Productor q3=new Productor(list);
		UseMoney u=new UseMoney(list);
		UseMoney u1=new UseMoney(list);
		UseMoney u2=new UseMoney(list);
		UseMoney u3=new UseMoney(list);
		UseMoney u4=new UseMoney(list);
		 ExecutorService e=Executors.newCachedThreadPool();
		 e.execute(q);
		 e.execute(q1);
		 e.execute(q2);
		 e.execute(q3);
		 e.execute(u3);
		 e.execute(u2);
		 e.execute(u1);
		 e.execute(u);
		 e.execute(u4);
		 e.shutdown();*/
	}

}

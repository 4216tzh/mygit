package com.java.test;

public class DaemonThread implements Runnable{

	@Override
	public void run() {
		int i=0;
		while(true) {
			i++;
			System.out.println(Thread.currentThread().getName()+", ÿª§œﬂ≥Ã:"+i);
			try {
				Thread.sleep(1000*2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		Thread t=new Thread(new DaemonThread(), "test");
		t.setDaemon(true);
		t.start();
		Thread.sleep(1000*10);
		
	}

}

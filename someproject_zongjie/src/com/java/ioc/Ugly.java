package com.java.ioc;

import java.util.concurrent.locks.LockSupport;

public class Ugly {
	
	public static void main(String[] args) {
		LockSupport.park();
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			LockSupport.unpark(Thread.currentThread());
		}
		
	}
	
	

}

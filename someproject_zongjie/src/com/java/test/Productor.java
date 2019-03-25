package com.java.test;

import java.util.List;

public class Productor implements Runnable{
	
	private List<String> list;
	
	public Productor(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		while(true){
			
			/*TestMain.lock.lock();  
	        try {  
	            if(1 == list.size()){  
	                System.out.println("���������ģ������������...");  
	                TestMain.full.await();  
	            }  
	            list.add("test");  
	            TestMain.empty.signal();  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }finally{  
	        	TestMain.lock.unlock();  
	        } */
			TestMain.lock.lock();
			try{
				if(1==list.size()){
					System.out.println("������");
					TestMain.full.await();
				}
				list.add("test");
				TestMain.empty.signal();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				TestMain.lock.unlock();
			}
		}
	}
}

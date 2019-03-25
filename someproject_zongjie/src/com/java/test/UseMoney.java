package com.java.test;

import java.util.List;

public class UseMoney implements Runnable{
	
	private List<String> list;
	
	public UseMoney(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		while(true){
			
			/*TestMain.lock.lock();  
	         try {  
	             if(list.size() == 0){  
	                 System.out.println("�����ǿյģ�������ٳ�...");  
	                 TestMain.empty.await();  
	             }  
	             list.remove(0);  
	             TestMain.full.signal();  
	         } catch (InterruptedException e) {  
	             e.printStackTrace();  
	         }finally{  
	        	 TestMain.lock.unlock();  
	         }  */
	         
			TestMain.lock.lock();
			try{
				if(0==list.size()){
					System.out.println("������.....");
					TestMain.empty.await();
				}
				list.remove(0);
				TestMain.full.signal();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				TestMain.lock.unlock();
			}
		}
	}

}

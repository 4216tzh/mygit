package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 消费对象
 * @author tzh
 *
 */
public class Computer {
	
	private String name;
	
	private String address;
	
	private static boolean isEmpty=true;
	
	private static ReentrantLock lock=new ReentrantLock();
	
	private static Condition Empty=lock.newCondition();
	
	private static Condition full=lock.newCondition();
	
	BlockingQueue<String> queue=new LinkedBlockingQueue<String>();
	
	private static List<String> list=new ArrayList<String>();
	
	public void indexSet(String str){
		lock.lock();  
        try {  
            if(1 == list.size()){  
                System.out.println("篮子是满的，待会儿再生产...");  
                full.await();  
            }  
            list.add(str);  
            Empty.signal();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }finally{  
            lock.unlock();  
        }  
	}
	
	public String indexGet(){
		 String m = null;  
         lock.lock();  
         try {  
             if(list.size() == 0){  
                 System.out.println("篮子是空的，待会儿再吃...");  
                 Empty.await();  
             }  
             m = list.remove(0);  
             full.signal();  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }finally{  
             lock.unlock();  
         }  
         return m;  
	}
	
	
	
	
	public  void set(String str){
		try{
			lock.lock();
			while(isEmpty){
				Empty.await();
			}
			list.add(str);
			System.out.println("生产者:"+str);
			isEmpty=true;
			Empty.signal();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public  void get(){
		try{
			lock.lock();
			while(!isEmpty){
				Empty.await();
			}
			System.out.println("消费者:");
			isEmpty=false;
			Empty.signal();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	 public synchronized void push(String name,String address){
	        try {
	            //不能用 if，因为可能有多个线程
	            while(!isEmpty){//进入到while语句内，说明 isEmpty==false，那么表示有数据了，不能生产，必须要等待消费者消费
	                this.wait();//导致当前线程等待，进入等待池中，只能被其他线程唤醒
	            }
	             
	            //-------生产数据开始-------
	            this.name = name;
	            //延时代码
	            Thread.sleep(10);
	            this.address = address;
	            //-------生产数据结束-------
	            isEmpty = false;//设置 isEmpty 为 false,表示已经有数据了
	            this.notify();//生产完毕，唤醒消费者
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	         
	    }
	 
	public synchronized void pop(){
		try {
            //不能用 if，因为可能有多个线程
            while(isEmpty){//进入 while 代码块，表示 isEmpty==true,表示为空，等待生产者生产数据，消费者要进入等待池中
                this.wait();//消费者线程等待
            }
            //-------消费开始-------
            Thread.sleep(10);
            System.out.println(this.name+"---"+this.address);
            //-------消费结束------
            isEmpty = true;//设置 isEmpty为true，表示需要生产者生产对象
            this.notify();//消费完毕，唤醒生产者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Computer [name=" + name + ", address=" + address + "]";
	}
	
}

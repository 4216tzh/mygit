package com.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ���Ѷ���
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
                System.out.println("���������ģ������������...");  
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
                 System.out.println("�����ǿյģ�������ٳ�...");  
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
			System.out.println("������:"+str);
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
			System.out.println("������:");
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
	            //������ if����Ϊ�����ж���߳�
	            while(!isEmpty){//���뵽while����ڣ�˵�� isEmpty==false����ô��ʾ�������ˣ���������������Ҫ�ȴ�����������
	                this.wait();//���µ�ǰ�̵߳ȴ�������ȴ����У�ֻ�ܱ������̻߳���
	            }
	             
	            //-------�������ݿ�ʼ-------
	            this.name = name;
	            //��ʱ����
	            Thread.sleep(10);
	            this.address = address;
	            //-------�������ݽ���-------
	            isEmpty = false;//���� isEmpty Ϊ false,��ʾ�Ѿ���������
	            this.notify();//������ϣ�����������
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	         
	    }
	 
	public synchronized void pop(){
		try {
            //������ if����Ϊ�����ж���߳�
            while(isEmpty){//���� while ����飬��ʾ isEmpty==true,��ʾΪ�գ��ȴ��������������ݣ�������Ҫ����ȴ�����
                this.wait();//�������̵߳ȴ�
            }
            //-------���ѿ�ʼ-------
            Thread.sleep(10);
            System.out.println(this.name+"---"+this.address);
            //-------���ѽ���------
            isEmpty = true;//���� isEmptyΪtrue����ʾ��Ҫ��������������
            this.notify();//������ϣ�����������
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

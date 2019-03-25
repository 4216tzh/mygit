package com.java.thread;

import java.util.List;

import com.java.annonation.AutoWire;

/**
 * 如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。
 * @author tzh
 *
 */
public class FriThread extends Thread{
	
	@AutoWire(description="共享变量")
	private List<String> list=null;
	
	private String name;
	
	public FriThread(String name,List<String> list){
		this.list=list;
		this.name=name;
	}

	@Override
	public void run() {
		while(true){
		synchronized(list){
			try{
				if(list.size()==1){
					list.wait();
				}
				list.add("test"+list.size());
					System.out.println("线程"+name+"，获取锁");
					this.list.notify();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}

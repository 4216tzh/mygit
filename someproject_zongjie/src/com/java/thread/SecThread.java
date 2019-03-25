package com.java.thread;

import java.util.ArrayList;
import java.util.List;

import com.java.annonation.AutoWire;

public class SecThread extends Thread{
	
	@AutoWire(description="共享变量")
	private List<String> list=null;
	
	private String name;
	
	public SecThread(String name,List<String> list){
		this.list=list;
		this.name=name;
	}

	@Override
	public void run() {
		while(true){
		synchronized(list) {
			try{
			if(list.size()==0){
				list.wait();
			}
			list.remove(0);
				System.out.println("线程"+name+"，获取锁");
				this.list.notify();
			}catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<String> list=new ArrayList<String>();
		String str="test";
		FriThread fri=new FriThread(str, list);
		SecThread sec=new SecThread(str+"sss", list);
		System.out.println(fri.getList().equals(sec.getList()));
		fri.start();
		sec.start();
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}

package com.java.thread;

import java.util.List;

import com.java.annonation.AutoWire;

/**
 * ���һ����̳�Thread�����ʺ���Դ�����������ʵ����Runable�ӿڵĻ���������׵�ʵ����Դ����
 * @author tzh
 *
 */
public class FriThread extends Thread{
	
	@AutoWire(description="�������")
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
					System.out.println("�߳�"+name+"����ȡ��");
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

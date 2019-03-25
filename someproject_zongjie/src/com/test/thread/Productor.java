package com.test.thread;

import java.util.List;

/**
 * ������
 * @author tzh
 *
 */
public class Productor implements Runnable{
	
	private List<String> queue;
	
	public Productor(List<String> queue){
		this.queue=queue;
	}

	@Override
	public void run() {
		while(true){
			MainTest.Lock.lock();
			try{
				if(queue.size()>0){
					System.out.println("�������Ѿ���������");
					MainTest.Full.await();
				}
				queue.add("test");
				MainTest.Empty.signal();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				MainTest.Lock.unlock();
			}
		}
		
		
	}

	public List<String> getQueue() {
		return queue;
	}

	public void setQueue(List<String> queue) {
		this.queue = queue;
	}
	
}

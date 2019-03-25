package com.test.thread;

import java.util.List;

/**
 * 消费者
 * @author tzh
 *
 */
public class Consume implements Runnable{
	
	private List<String> queue;
	
	public Consume(List<String> queue){
		this.queue=queue;
	}

	@Override
	public void run() {
		while(true){
			MainTest.Lock.lock();
			try{
				if(queue.size()<1){
					System.out.println("消费者消费完了");
					MainTest.Empty.await();
				}
				queue.remove(0);
				MainTest.Full.signal();
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

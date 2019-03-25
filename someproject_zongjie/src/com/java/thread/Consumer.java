package com.java.thread;

public class Consumer implements Runnable{
	
	private Computer computer=null;
	
	
	public Consumer(Computer computer){
		this.computer=computer;
	}

	@Override
	public void run() {
		while(true){
            //消费对象
			//computer.pop();
			computer.indexGet();
		}
		
	}
	
	public static void main(String[] args) {
		Computer c=new Computer();
		Thread t=new Thread(new Consumer(c));
		Thread t1=new Thread(new Productor(c));
		t.start();
		t1.start();
	}

}

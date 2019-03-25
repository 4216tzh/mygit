package com.java.thread;

public class Productor implements Runnable {
	
	private Computer computer=null;
	
	public Productor(Computer computer){
		this.computer=computer;
	}
	@Override
	public void run() {
		while(true){
		//生产对象
        for(int i = 0 ; i < 50 ; i++){
            
            if(i%2==0){
            	computer.indexSet("南昌");
            }else{
            	computer.indexSet("上海");
            }
        }
		}
	}

}

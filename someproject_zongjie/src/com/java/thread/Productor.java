package com.java.thread;

public class Productor implements Runnable {
	
	private Computer computer=null;
	
	public Productor(Computer computer){
		this.computer=computer;
	}
	@Override
	public void run() {
		while(true){
		//��������
        for(int i = 0 ; i < 50 ; i++){
            
            if(i%2==0){
            	computer.indexSet("�ϲ�");
            }else{
            	computer.indexSet("�Ϻ�");
            }
        }
		}
	}

}

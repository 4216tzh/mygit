package com.java.thread;

/**
 * synchronized�ؼ������η���(������),һ��class���кܶ�ʵ������
 * ���ξ�̬����(����),����һ����ֻ��һ��class�����
 * ͬ�������(���п������ö�������������)
 * @author tzh
 * java�Ķ�������������java�Ķ����������������ĸ����ϻ����Ϻ���������һ�µģ����ǣ�������ʵ�����кܴ������ģ������������ڶ���ʵ��������
 * ����һ������ʵ���ϵģ�������������ľ�̬��������һ�����class�����ϵġ�����֪������Ķ���ʵ�������кܶ��������ÿ����ֻ��һ��class����
 * ���Բ�ͬ����ʵ���Ķ������ǻ������ŵģ�����ÿ����ֻ��һ��������������һ�����ע����ǣ���ʵ����ֻ��һ�������ϵĶ�������������ʵ���ڵģ�
 * ��ֻ���������������������ʵ�������;�̬�����������
 */
public class ManyThread {
	
	/**
	 * ������������߳��£�ֻ��һ��ʵ��������Է���
	 */
	public synchronized void funZero(){
		for(int i=0;i<5;i++){
		System.out.println("���Ƿ��㣺funZero"+Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	/**
	 * ����
	 */
	public static synchronized void funOne(){
		for(int i=0;i<5;i++){
		System.out.println("����һ����funOne"+Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
	}
	
	public void funTwo(){
		for(int i=0;i<5;i++){
		System.out.println("��ô��������funOne"+Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void funThree(){
		/**
		 * ����
		 */
		synchronized(ManyThread.class){
			for(int i=0;i<5;i++){
			System.out.println("��ϵ���꣺funThree"+Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
		}
	}
	
	public static void main(String[] args) {
		ManyThread m=new ManyThread();
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//m.funZero();
				funOne();
			}
		});
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//funOne();
				//m.funZero();
				m.funThree();
			}
		});
		t.start();
		t1.start();
	}

}

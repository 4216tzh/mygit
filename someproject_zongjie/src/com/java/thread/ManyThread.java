package com.java.thread;

/**
 * synchronized关键字修饰方法(对象锁),一个class类有很多实例对象
 * 修饰静态方法(类锁),但是一个类只有一个class类对象
 * 同步代码块(其中可以设置对象锁或者类锁)
 * @author tzh
 * java的对象锁和类锁：java的对象锁和类锁在锁的概念上基本上和内置锁是一致的，但是，两个锁实际是有很大的区别的，对象锁是用于对象实例方法，
 * 或者一个对象实例上的，类锁是用于类的静态方法或者一个类的class对象上的。我们知道，类的对象实例可以有很多个，但是每个类只有一个class对象，
 * 所以不同对象实例的对象锁是互不干扰的，但是每个类只有一个类锁。但是有一点必须注意的是，其实类锁只是一个概念上的东西，并不是真实存在的，
 * 它只是用来帮助我们理解锁定实例方法和静态方法的区别的
 */
public class ManyThread {
	
	/**
	 * 对象锁，多个线程下，只有一个实例对象可以访问
	 */
	public synchronized void funZero(){
		for(int i=0;i<5;i++){
		System.out.println("这是放零：funZero"+Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	/**
	 * 类锁
	 */
	public static synchronized void funOne(){
		for(int i=0;i<5;i++){
		System.out.println("那是一个鬼：funOne"+Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
	}
	
	public void funTwo(){
		for(int i=0;i<5;i++){
		System.out.println("那么就这样：funOne"+Thread.currentThread().getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void funThree(){
		/**
		 * 类锁
		 */
		synchronized(ManyThread.class){
			for(int i=0;i<5;i++){
			System.out.println("佛系少年：funThree"+Thread.currentThread().getName());
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

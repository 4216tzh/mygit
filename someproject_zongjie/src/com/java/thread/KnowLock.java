package com.java.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 总的来说，Lock和synchronized有以下几点不同：
 * (1) Lock是一个接口，是JDK层面的实现；而synchronized是Java中的关键字，是Java的内置特性，是JVM层面的实现；
 * (2) synchronized 在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，
   	         则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
 * (3) Lock 可以让等待锁的线程响应中断，而使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
 * (4) 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到；
 * (5) Lock可以提高多个线程进行读操作的效率。
 *  在性能上来说，如果竞争资源不激烈，两者的性能是差不多的。而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。
 *  所以说，在具体使用时要根据适当情况选择。
 * @author tzh
 *
 */
public class KnowLock {
	private final ReentrantLock lock=new ReentrantLock();
	
	BlockingQueue<Object> mapsss=new LinkedBlockingQueue<Object>(); 
	
	//读写锁
	//private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	//等待 this.conditionA.await();
	//唤醒 this.conditionA.signal();
	private Condition conditionA=lock.newCondition();
	
	private Condition conditionB=lock.newCondition();
	
	private String text;
	
	private Integer account;
	
	/**
	 * select distinct name from stu where name not in(select distinct name from stu where fs<80) 
	 * @return
	 */
	public String getText() {
		
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Integer getAccount() {
		return account;
	}


	public void setAccount(Integer account) {
		this.account = account;
	}
	
	public Condition getConditionA() {
		return conditionA;
	}


	public void setConditionA(Condition conditionA) {
		this.conditionA = conditionA;
	}


	public Condition getConditionB() {
		return conditionB;
	}


	public void setConditionB(Condition conditionB) {
		this.conditionB = conditionB;
	}


	/**
	 * 直接加锁
	 * @param knowLock
	 */
	public void test(){
		lock.lock();
		try{
			for(int i=0;i<5;i++){
			this.account++;
			System.out.println(Thread.currentThread().getName()+":"+this.account);
			Thread.sleep(500);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();//调用方法释放锁
		}
	}
	/**
	 * @throws InterruptedException 
	 * 
	 */
	public void tryLocks() throws InterruptedException{
		//这个方法在拿不到锁时会等待一定的时间，在时间期限之内如果还拿不到锁，
		//就返回false，同时可以响应中断
		if(lock.tryLock(5,TimeUnit.SECONDS)){
			try{
				for(int i=0;i<5;i++){
					account++;
					System.out.println(Thread.currentThread().getName()+":"+account);
					Thread.sleep(500);
					}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				lock.unlock();//调用方法释放锁
			}
		}
	}
	/**
	 * 
	 */
	public void tryLock(){
		//这个方法无论如何都会立即返回
		if(lock.tryLock()){
			try{
				for(int i=0;i<5;i++){
					account++;
					System.out.println(Thread.currentThread().getName()+":"+account);
					Thread.sleep(500);
					}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				lock.unlock();//调用方法释放锁
			}
		}
	}
	
	/**
	 * lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程 正在等待获取锁，则这个线程能够 响应中断，即中断线程的等待状态。
	 * 例如，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，
	 * 那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
	 * @throws InterruptedException
	 */
	public void Interrupted() throws InterruptedException{
		lock.lockInterruptibly();
		
		try{
			for(int i=0;i<5;i++){
				this.account++;
				System.out.println(Thread.currentThread().getName()+":"+this.account);
				Thread.sleep(500);
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();//调用方法释放锁
		}
	}
	
	public void fun(){
		lock.lock();
		try{
			System.out.println("线程名称"+Thread.currentThread().getName()+":"+System.currentTimeMillis());
			conditionA.await();
			System.out.println();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		KnowLock knowLock=new KnowLock();
		knowLock.setAccount(0);
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					knowLock.Interrupted();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					knowLock.Interrupted();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		t1.start();
		 //t1.interrupt();响应中断
	}
	

}

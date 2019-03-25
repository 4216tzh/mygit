package com.java.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * �ܵ���˵��Lock��synchronized�����¼��㲻ͬ��
 * (1) Lock��һ���ӿڣ���JDK�����ʵ�֣���synchronized��Java�еĹؼ��֣���Java���������ԣ���JVM�����ʵ�֣�
 * (2) synchronized �ڷ����쳣ʱ�����Զ��ͷ��߳�ռ�е�������˲��ᵼ����������������Lock�ڷ����쳣ʱ�����û������ͨ��unLock()ȥ�ͷ�����
   	         ��ܿ�����������������ʹ��Lockʱ��Ҫ��finally�����ͷ�����
 * (3) Lock �����õȴ������߳���Ӧ�жϣ���ʹ��synchronizedʱ���ȴ����̻߳�һֱ�ȴ���ȥ�����ܹ���Ӧ�жϣ�
 * (4) ͨ��Lock����֪����û�гɹ���ȡ������synchronizedȴ�޷��쵽��
 * (5) Lock������߶���߳̽��ж�������Ч�ʡ�
 *  ����������˵�����������Դ�����ң����ߵ������ǲ��ġ�����������Դ�ǳ�����ʱ�����д����߳�ͬʱ����������ʱLock������ҪԶԶ����synchronized��
 *  ����˵���ھ���ʹ��ʱҪ�����ʵ����ѡ��
 * @author tzh
 *
 */
public class KnowLock {
	private final ReentrantLock lock=new ReentrantLock();
	
	BlockingQueue<Object> mapsss=new LinkedBlockingQueue<Object>(); 
	
	//��д��
	//private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	//�ȴ� this.conditionA.await();
	//���� this.conditionA.signal();
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
	 * ֱ�Ӽ���
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
			lock.unlock();//���÷����ͷ���
		}
	}
	/**
	 * @throws InterruptedException 
	 * 
	 */
	public void tryLocks() throws InterruptedException{
		//����������ò�����ʱ��ȴ�һ����ʱ�䣬��ʱ������֮��������ò�������
		//�ͷ���false��ͬʱ������Ӧ�ж�
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
				lock.unlock();//���÷����ͷ���
			}
		}
	}
	/**
	 * 
	 */
	public void tryLock(){
		//�������������ζ�����������
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
				lock.unlock();//���÷����ͷ���
			}
		}
	}
	
	/**
	 * lockInterruptibly()�����Ƚ����⣬��ͨ���������ȥ��ȡ��ʱ������߳� ���ڵȴ���ȡ����������߳��ܹ� ��Ӧ�жϣ����ж��̵߳ĵȴ�״̬��
	 * ���磬�������߳�ͬʱͨ��lock.lockInterruptibly()���ȡĳ����ʱ��������ʱ�߳�A��ȡ�����������߳�Bֻ���ڵȴ���
	 * ��ô���߳�B����threadB.interrupt()�����ܹ��ж��߳�B�ĵȴ����̡�
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
			lock.unlock();//���÷����ͷ���
		}
	}
	
	public void fun(){
		lock.lock();
		try{
			System.out.println("�߳�����"+Thread.currentThread().getName()+":"+System.currentTimeMillis());
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
		 //t1.interrupt();��Ӧ�ж�
	}
	

}

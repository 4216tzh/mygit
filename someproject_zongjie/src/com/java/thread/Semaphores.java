package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 	public void acquire() 
	// ���Ի��һ��׼�����ɡ����޷���ã����̻߳�ȴ���ֱ�����߳��ͷ�һ����ɻ��ߵ�ǰ�̱߳��жϡ�
	public void acquireUninterruptibly()
	// �� acquire()������ͬ���������䲻��Ӧ�жϡ�
	public boolean tryAcquire()
	// ���Ի��һ����ɣ�����ɹ�����true��ʧ���򷵻�false���䲻����еȴ����������ء�
	public boolean tryAcquire(long timeout, TimeUnit unit)
	// �� tryAcquire()������ͬ�������������ӳ��Ե�ʱ���ʱ�䵥λ��
	public void release()
	// �������̷߳�����Դ�������ͷ�һ����ɣ���ʹ�����ȴ���ɵ��߳̿��Խ�����Դ���ʡ�
 * @author tzh
 *
 */
public class Semaphores implements Runnable{
	
	/**
	 * ����ӵ�� 20 ���̵߳��̳߳أ������� 5 ���̻߳�ȡ���֤���ź��������н����ÿ��ֻ�� 5 ���߳�ͬʱ��ȡ���֤�����У�
	 * Ҳ���ǿ������Ĳ�����Ϊ 5��ʹ����֮����� release()�ͷ���ɣ��黹���ź�����
	 */
	final Semaphore semp = new Semaphore(5,true);
	
	 @Override
	    public void run() {
	        try {
	            semp.acquire();
	            Thread.sleep(2000);
	            System.out.println(Thread.currentThread().getName()+",done!");
	            semp.release();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    
	 
	    public static void main(String[] args) {
	        final Semaphores semapDemo = new Semaphores();
	        ExecutorService executorService = Executors.newFixedThreadPool(20);
	        for (int i = 0; i < 20;i++) {
	            executorService.execute(semapDemo);
	        }
	        executorService.shutdown();
	    }
}

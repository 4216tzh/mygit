package com.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 	public void acquire() 
	// 尝试获得一个准入的许可。若无法获得，则线程会等待，直到有线程释放一个许可或者当前线程被中断。
	public void acquireUninterruptibly()
	// 与 acquire()基本相同，区别是其不响应中断。
	public boolean tryAcquire()
	// 尝试获得一个许可，如果成功返回true，失败则返回false，其不会进行等待，立即返回。
	public boolean tryAcquire(long timeout, TimeUnit unit)
	// 与 tryAcquire()基本相同，区别是其增加尝试的时间和时间单位。
	public void release()
	// 用于在线程访问资源结束后，释放一个许可，以使其他等待许可的线程可以进行资源访问。
 * @author tzh
 *
 */
public class Semaphores implements Runnable{
	
	/**
	 * 申请拥有 20 个线程的线程池，和允许 5 个线程获取许可证的信号量。运行结果，每次只有 5 个线程同时获取许可证并运行，
	 * 也就是控制最大的并发数为 5，使用完之后调用 release()释放许可，归还给信号量。
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

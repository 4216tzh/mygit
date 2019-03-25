package com.java.threadpool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  总结一些java自带的线程池
 *  a、newFixedThreadPool 创建一个固定长度的线程池，当到达线程最大数量时，线程池的规模将不再变化。
	b、newCachedThreadPool 创建一个可缓存的线程池，如果当前线程池的规模超出了处理需求，将回收空的线程；当需求增加时，会增加线程数量；线程池规模无限制。
	c、newSingleThreadPoolExecutor 创建一个单线程的Executor，确保任务对了，串行执行
	d、newScheduledThreadPool 创建一个固定长度的线程池，而且以延迟或者定时的方式来执行，类似Timer；
	小结一下：在线程池中执行任务比为每个任务分配一个线程优势更多，通过重用现有的线程而不是创建新线程，可以在处理多个请求时分摊线程创建和销毁产生的巨大的开销。
	当请求到达时，通常工作线程已经存在，提高了响应性；通过配置线程池的大小，可以创建足够多的线程使CPU达到忙碌状态，还可以防止线程太多耗尽计算机的资源。
 * @author tzh
 *
 */
public class ThreadPool {
	/**
	 * 1.newCachedThreadPool() 	-缓存型池子，先查看池中有没有以前建立的线程，如果有，就reuse.如果没有，就建一个新的线程加入池中
		-缓存型池子通常用于执行一些生存期很短的异步型任务,因此在一些面向连接的daemon型SERVER中用得不多。
		-能reuse的线程，必须是timeout IDLE内的池中线程，缺省timeout是60s,超过这个IDLE时长，线程实例将被终止及移出池。
  		注意，放入CachedThreadPool的线程不必担心其结束，超过TIMEOUT不活动，其会自动被终止。
		2.newFixedThreadPool	-newFixedThreadPool与cacheThreadPool差不多，也是能reuse就用，但不能随时建新的线程
		  -其独特之处:任意时间点，最多只能有固定数目的活动线程存在，此时如果有新的线程要建立，只能放在另外的队列中等待，
		         直到当前的线程中某个线程终止直接被移出池子,-和cacheThreadPool不同，FixedThreadPool没有IDLE机制
		 （可能也有，但既然文档没提，肯定非常长，类似依赖上层的TCP或UDP IDLE机制之类的),
		 所以FixedThreadPool多数针对一些很稳定很固定的正规并发线程，多用于服务器
		-从方法的源代码看，cache池和fixed 池调用的是同一个底层池，只不过参数不同:
			fixed池线程数固定，并且是0秒IDLE（无IDLE）
			cache池线程数支持0-Integer.MAX_VALUE(显然完全没考虑主机的资源承受能力），60秒IDLE  
		3.ScheduledThreadPool	-调度型线程池
			-这个池子里的线程可以按schedule依次delay执行，或周期执行
		4.SingleThreadExecutor	-单例线程，任意时间池中只能有一个线程
		 -用的是和cache池和fixed池相同的底层池，但线程数目是1-1,0秒IDLE（无IDLE）
	 * @param list
	 */
	public static void test(List<Thread> list,Integer xx){
		if(list==null||xx==null||list.size()<1||xx<1){
			return;
		}
		ExecutorService e=Executors.newFixedThreadPool(xx);
		for(Thread t:list){
			e.execute(t);
		}
		e.shutdown();
		System.out.println("main thread::"+new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date()));
	}
	/**
	 * 1、主线程的执行与线程池里的线程分开，有可能主线程结束了，但是线程池还在运行
	 * 2、放入线程池的线程并不一定会按其放入的先后而顺序执行
	 */
	public static void test1(List<Thread> list){
		if(list==null||list.size()<1){
			return;
		}
		ExecutorService e=Executors.newCachedThreadPool();
		for(Thread t:list){
			e.execute(t);
			
		}
		//让所有的入队任务都执行完毕
		//e.shutdown();//执行到此处并不会马上关闭线程池,但之后不能再往线程池中加线程，否则会报错
		e.shutdownNow();
		System.out.println("main thread::"+new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date()));
	}
	
	/**
	 * 创建线程，放入集合中
	 * @return
	 */
	public static List<Thread> addThread(Integer xx){
		if(xx==null||xx<1){
			return null;
		}
		
		List<Thread> list=Collections.synchronizedList(new ArrayList<Thread>());
		for(int x=0;x<xx;x++){
		Thread t=new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+","+new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date()));
			}
		});
		t.setName("test"+x);
		list.add(t);
		}
		return list;
	}
	
	public static void schedulePool(){
		ScheduledExecutorService executor =Executors.newScheduledThreadPool(1);
		
			//executor.schedule(new JumpPoolThread(String.valueOf(i)), 10, TimeUnit.SECONDS);
			/**
			 * 执行线程(任务)
			 * 初始化延时
			 * 间隔时间
			 * 计时单位
			 */
			executor.scheduleAtFixedRate(new JumpPoolThread(String.valueOf("test")), 1, 2, TimeUnit.SECONDS);
			//executor.scheduleWithFixedDelay(new JumpPoolThread(String.valueOf("test")), 10, 20, TimeUnit.SECONDS);
			
		
		//executor.shutdown();
		
		
	}
	
	public static void main(String[] args) {
		/*List<Thread> list=addThread(20);
		if(list!=null&&list.size()>0)
		test(list,list.size());
		test1(list);*/
		schedulePool();
		ReentrantLock lock=new ReentrantLock();
		lock.tryLock();
		lock.unlock();
		lock.lock();
	}

}

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
 *  �ܽ�һЩjava�Դ����̳߳�
 *  a��newFixedThreadPool ����һ���̶����ȵ��̳߳أ��������߳��������ʱ���̳߳صĹ�ģ�����ٱ仯��
	b��newCachedThreadPool ����һ���ɻ�����̳߳أ������ǰ�̳߳صĹ�ģ�����˴������󣬽����տյ��̣߳�����������ʱ���������߳��������̳߳ع�ģ�����ơ�
	c��newSingleThreadPoolExecutor ����һ�����̵߳�Executor��ȷ��������ˣ�����ִ��
	d��newScheduledThreadPool ����һ���̶����ȵ��̳߳أ��������ӳٻ��߶�ʱ�ķ�ʽ��ִ�У�����Timer��
	С��һ�£����̳߳���ִ�������Ϊÿ���������һ���߳����Ƹ��࣬ͨ���������е��̶߳����Ǵ������̣߳������ڴ���������ʱ��̯�̴߳��������ٲ����ľ޴�Ŀ�����
	�����󵽴�ʱ��ͨ�������߳��Ѿ����ڣ��������Ӧ�ԣ�ͨ�������̳߳صĴ�С�����Դ����㹻����߳�ʹCPU�ﵽæµ״̬�������Է�ֹ�߳�̫��ľ����������Դ��
 * @author tzh
 *
 */
public class ThreadPool {
	/**
	 * 1.newCachedThreadPool() 	-�����ͳ��ӣ��Ȳ鿴������û����ǰ�������̣߳�����У���reuse.���û�У��ͽ�һ���µ��̼߳������
		-�����ͳ���ͨ������ִ��һЩ�����ں̵ܶ��첽������,�����һЩ�������ӵ�daemon��SERVER���õò��ࡣ
		-��reuse���̣߳�������timeout IDLE�ڵĳ����̣߳�ȱʡtimeout��60s,�������IDLEʱ�����߳�ʵ��������ֹ���Ƴ��ء�
  		ע�⣬����CachedThreadPool���̲߳��ص��������������TIMEOUT���������Զ�����ֹ��
		2.newFixedThreadPool	-newFixedThreadPool��cacheThreadPool��࣬Ҳ����reuse���ã���������ʱ���µ��߳�
		  -�����֮��:����ʱ��㣬���ֻ���й̶���Ŀ�Ļ�̴߳��ڣ���ʱ������µ��߳�Ҫ������ֻ�ܷ�������Ķ����еȴ���
		         ֱ����ǰ���߳���ĳ���߳���ֱֹ�ӱ��Ƴ�����,-��cacheThreadPool��ͬ��FixedThreadPoolû��IDLE����
		 ������Ҳ�У�����Ȼ�ĵ�û�ᣬ�϶��ǳ��������������ϲ��TCP��UDP IDLE����֮���),
		 ����FixedThreadPool�������һЩ���ȶ��̶ܹ������沢���̣߳������ڷ�����
		-�ӷ�����Դ���뿴��cache�غ�fixed �ص��õ���ͬһ���ײ�أ�ֻ����������ͬ:
			fixed���߳����̶���������0��IDLE����IDLE��
			cache���߳���֧��0-Integer.MAX_VALUE(��Ȼ��ȫû������������Դ������������60��IDLE  
		3.ScheduledThreadPool	-�������̳߳�
			-�����������߳̿��԰�schedule����delayִ�У�������ִ��
		4.SingleThreadExecutor	-�����̣߳�����ʱ�����ֻ����һ���߳�
		 -�õ��Ǻ�cache�غ�fixed����ͬ�ĵײ�أ����߳���Ŀ��1-1,0��IDLE����IDLE��
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
	 * 1�����̵߳�ִ�����̳߳�����̷ֿ߳����п������߳̽����ˣ������̳߳ػ�������
	 * 2�������̳߳ص��̲߳���һ���ᰴ�������Ⱥ��˳��ִ��
	 */
	public static void test1(List<Thread> list){
		if(list==null||list.size()<1){
			return;
		}
		ExecutorService e=Executors.newCachedThreadPool();
		for(Thread t:list){
			e.execute(t);
			
		}
		//�����е��������ִ�����
		//e.shutdown();//ִ�е��˴����������Ϲر��̳߳�,��֮���������̳߳��м��̣߳�����ᱨ��
		e.shutdownNow();
		System.out.println("main thread::"+new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date()));
	}
	
	/**
	 * �����̣߳����뼯����
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
			 * ִ���߳�(����)
			 * ��ʼ����ʱ
			 * ���ʱ��
			 * ��ʱ��λ
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

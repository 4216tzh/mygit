package com.java.study;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OneTest {
	
	private static int size=5000000;
	
	public static volatile String[] str= new String[size];
	
	private static String[] arr=new String[size];
	
	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		for(int x=0;x<size;x++) {
			str[x]=String.valueOf((int)(Math.random()*100));
		}
		System.out.println("volatile:"+(System.currentTimeMillis()-start));
		
		long start2=System.currentTimeMillis();
		for(int x=0;x<size;x++) {
			arr[x]=String.valueOf((int)(Math.random()*100));
		}
		System.out.println(System.currentTimeMillis()-start2);
	}
	ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(10,// �����߳���
			20, // ����߳���
			10, // �����̴߳��ʱ��
			TimeUnit.MILLISECONDS,// ʱ�䵥λ
			new LinkedBlockingDeque<Runnable>(),// �̶߳���
			Executors.defaultThreadFactory()// �̹߳���
			//new AbortPolicy()// ��������,���ҵ�ǰ�߳����Ѿ���������߳���ʱ���쳣�������
	);


}

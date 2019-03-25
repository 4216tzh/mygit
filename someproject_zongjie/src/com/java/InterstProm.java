package com.java;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.java.file.excel.Customer;

public class InterstProm {
	private static volatile Customer c=new Customer();
	
	private static int flag=0;
	
	
	public static void main(String[] args) {
		byte b=-127;
		//byte转化为十进制时，与上0xff，保持数据的一致性
		int x=b&0xff;
		System.out.println(x);
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
		System.out.println(df.format(new Date()));
		/*for(;count<Integer.MAX_VALUE;count++){
			
		}*/
		System.out.println("volatile end>>>"+df.format(new Date()));
		System.out.println("start:::"+df.format(new Date()));
		for(int xx=0;xx<Integer.MAX_VALUE;xx++){
			
		}
		System.out.println("end:::"+df.format(new Date()));
		/*try {
			Class<?> clazz=Class.forName("com.java.file.excel.Customer");
			new ConcurrentHashMap<String ,Object>();
			Object obj=clazz.newInstance();
			Customer c=(Customer)obj;
			BeanInfo beanInfo=Introspector.getBeanInfo(c.getClass(), Object.class); 
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int y=0;y<5;y++){
					try {
						flag++;
						Integer xxx=c.getAge()==null?0:c.getAge();
						xxx++;
						c.setAge(xxx);
						System.out.println(Thread.currentThread().getName()+",flag:"+flag+",count:"+xxx);
						Thread.sleep(500);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int x=0;x<5;x++){
					try {
						flag++;
						Integer xxx=c.getAge()==null?0:c.getAge();
						xxx++;
						c.setAge(xxx);
						System.out.println(Thread.currentThread().getName()+",flag:"+flag+",count:"+xxx);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		t.setName("tname");
		t1.setName("t1name");
		t.start();
		t1.start();
	}

}

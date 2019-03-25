package com.java;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SomeHttp {
	
	Stack<String> stack=new Stack<String>();
	List<String> list=new ArrayList<String>();
	List<String> link=new LinkedList<String>();
	Set<String> tSet=new TreeSet<String>();
	Set<String> hSet=new HashSet<String>();
	BlockingQueue<String> queue=new LinkedBlockingQueue<String>();
	Map<String, String> tMap=new TreeMap<String, String>();
	Map<String, String> hMap=new HashMap<String, String>();
	ConcurrentMap<String, String> map=new ConcurrentHashMap<String, String>();
	List<String> synList=new CopyOnWriteArrayList<String>();
	List<String> safeList=Collections.synchronizedList(new ArrayList<String>());
	ReentrantLock lock=new ReentrantLock();
	Condition condition=lock.newCondition();
	
	public static void main(String[] args) throws Exception {
		
		Class<?> clazz=People.class.getClass();
		Object obj=clazz.newInstance();
		Field[] fields=clazz.getDeclaredFields();
		Method[] methods=clazz.getDeclaredMethods();
		for(Field f:fields){
			f.setAccessible(true);
			Object s=f.get(obj);
			System.out.println(s.toString());
		}
		for(Method m:methods){
			m.setAccessible(true);
			Object s=m.invoke(obj, null);
			System.out.println(s.toString());
		}
	}

}

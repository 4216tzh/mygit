package com.java.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import com.java.file.excel.Customer;
import com.mysql.jdbc.log.Slf4JLogger;

/**
 * 一些常用集合
 * @author tzh
 *
 */
public class CollectionGen {
	//使用volatile需要保持此变量在程序中的独立性
	//private static volatile List<String> tList=new ArrayList<String>();
	
		public static void main(String[] args) {
			Byte b=0x7f;
			//System.out.println(b);
			Integer i=0x7fffffff;
			//System.out.println(i);
			//CollectionGen c=new CollectionGen();
			Stack<String> stack=new Stack<String>();
			stack.push("1231");
			stack.push("1234");
			stack.push("1235");
			stack.push("1236");
			String str=stack.pop();//先进后出
			BlockingQueue<String> queue=new LinkedBlockingQueue<String>();
			queue.add("999");
			queue.add("888");
			queue.add("777");
			queue.add("666");
			System.out.println("stack:"+str);
			System.out.println("queue:"+queue.poll());//先进先出
			//List<String> test=new  CopyOnWriteArrayList<String>();
			/*TEST.add("");
			map.put("", "");
			String s=new String("123,");
			System.out.println(s.endsWith(","));
			System.out.println(s.startsWith("1"));
			System.out.println(String.format("%s%s", s,"234"));*/
			list.add(new Customer());
			list.add(new Customer());
			list.add(new Customer());
			list.add(new Customer());
			list.add(new Customer());
			list.add(new Customer());
			for (List<Customer> ss=list;;) {
				System.err.println("xxx");
			}
			
		}
	/**
	 * 多线程下安全集合
	 */
	private BlockingQueue<Customer> queue=new LinkedBlockingQueue<Customer>();
	
	private static List<Customer> list=Collections.synchronizedList(new ArrayList<Customer>());
	
	private static final  List<String> TEST=new  CopyOnWriteArrayList<String>();//使用lock可重入锁，实现线程安全
	
	private Vector<Customer> vec=new Vector<Customer>();
	
	private static ConcurrentMap<String, Object> map=new ConcurrentHashMap<String, Object>();
	
	private Map<String, Object> table=new Hashtable<String, Object>();
	
	private Map<String, Object> maps=Collections.synchronizedMap(new HashMap<String, Object>());

	
	public BlockingQueue<Customer> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Customer> queue) {
		this.queue = queue;
	}

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public Vector<Customer> getVec() {
		return vec;
	}

	public void setVec(Vector<Customer> vec) {
		this.vec = vec;
	}

	public ConcurrentMap<String, Object> getMap() {
		return map;
	}

	public void setMap(ConcurrentMap<String, Object> map) {
		this.map = map;
	}
	
	public Map<String, Object> getTable() {
		return table;
	}

	public void setTable(Map<String, Object> table) {
		this.table = table;
	}

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	
	
	
}

package com.java.threadpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Queue;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.java.connection.ConnectionPool;

/**
 * 
 * @author TZH
 *
 */
public class ThreadContainer {
	
	private static final ThreadLocal<Connection> Local=new ThreadLocal<Connection>();
	
	public static void main(String[] args) {
		/*Local.set("test");
		System.out.println("value:"+Local.get());
		Comparator<Integer> cpt2 = (x,y) -> Integer.compare(x,y);
		  TreeSet<Integer> set2 = new TreeSet<Integer>(cpt2);
		  set2.add(12);
		  set2.add(14);
		  set2.add(13);
		  set2.add(31);
		  System.out.println(set2.size());*/
		initConnPool(20);
		threadPoolTest();
		
	}
	
	public static void threadPoolTest() {
		final int max=30;
		BlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor exec=new ThreadPoolExecutor(max/2, max, 20000, TimeUnit.SECONDS, queue);
		for(int x=0;x<max;x++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					for(int m=0;m<3;m++) {
						try {
							Connection con=getConn();
							PreparedStatement pstm = con.prepareStatement("update task set TASK_RUN_STATUS = ? where id = ? and TASK_RUN_STATUS = ?");
							 pstm.setInt(1, 2);
			                    pstm.setInt(2, m);
			                    pstm.setInt(3, 1);
			                    int upRec = pstm.executeUpdate();
			                    closeConn(con);
			                    System.out.println("当前线程名:"+Thread.currentThread().getName()+",修改数据:"+upRec);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		exec.shutdown();
	}
	
	public static void initConnPool(int maxNum) {
		for(int x=0;x<maxNum;x++) {
			Connection con=ConnectionPool.getConn();
			Local.set(con);
		}
	}
	
	public static Connection getConn() {
		if(Local.get()==null) {
			Connection conn=ConnectionPool.getConn();
			Local.set(conn);
		}
		return Local.get();
	}
	
	public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				Local.remove();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

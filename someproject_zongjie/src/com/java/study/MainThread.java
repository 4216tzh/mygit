package com.java.study;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.java.connection.PorpertiesUtil;
import com.java.threadpool.ThreadContainer;

public class MainThread {
	
		public static void main(String[] args) throws Exception {
			Class.forName(PorpertiesUtil
					.getProperties(PorpertiesUtil.MYSQLDRIVERNAME));
			 ExecutorService executorService = Executors.newFixedThreadPool(30);
		        List<Future<Void>> futures = new ArrayList<Future<Void>>();
		        ThreadContainer.initConnPool(40);
		        
		        // ÿ��ID��20���߳�ȥ������������
		        for (int i=0; i<20; i++) {
		            for (int j=0; j<3; j++) {
		                final int id = j;
		                futures.add(executorService.submit(new Callable<Void>() {
		                    public Void call() throws Exception {
		                    	Connection con=ThreadContainer.getConn();
		                        // con.setAutoCommit(false);        // ���Զ��ύ����
		                        //PreparedStatement pstm = con.prepareStatement("update task set TASK_RUN_STATUS = ? where id = ? and TASK_RUN_STATUS = ?");
		                    	PreparedStatement pstm=con.prepareStatement("update task set TASK_RUN_STATUS = TASK_RUN_STATUS-1 where id = ? and TASK_RUN_STATUS >0");
		                        pstm.setInt(1, id);
		                        int upRec = pstm.executeUpdate();
		                        // ��ӡ���µļ�¼����
		                        System.out.println("Thread:" + Thread.currentThread().getName() + " updated(id=" + id + "):" + upRec + " records...");
		                        // Thread.sleep(1000);      // �������ύ֮ǰ�����̶߳�������ֱ�����ض���¼�ĸ����ύ
		                        // con.commit();
		                        con.close();
		                        pstm.close();
		                        return null;
		                    }
		                }));
		            }
		        }
		        executorService.shutdown();
		}
		
		

}

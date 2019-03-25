package com.java.connection;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 
 * @author tzh
 *
 */
public class DataSourceFactory {
	
	public static void main(String[] args) {
		test("com.mysql.jdbc.Driver","sa123456","root",
				"jdbc:mysql://192.168.120.228:3306/sajt-xxgl?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull");
	}
	
	public  static  void test(String className,String passWord,String userName,String url){
		BasicDataSource bds=new BasicDataSource();
		bds.setDriverClassName(className);
		bds.setPassword(passWord);
		bds.setUsername(userName);
		bds.setUrl(url);
		try {
			Connection conn=bds.getConnection();
			System.out.println(conn.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

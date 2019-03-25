package com.java.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

	private static Connection conn;

	public static Connection getConn() {

		conn = openDB();
		if (conn != null) {
			//System.out.println("数据库连接成功!!!");
		}
		return conn;

	}

	private static Connection openDB() {
		ThreadLocal<String> t=new ThreadLocal<String>();
		Connection conn = null;
		String driverName = PorpertiesUtil
				.getProperties(PorpertiesUtil.MYSQLDRIVERNAME);
		String url = PorpertiesUtil.getProperties(PorpertiesUtil.MYSQLURL);
		String pass = PorpertiesUtil.getProperties(PorpertiesUtil.MYSQLPASS);
		String user = PorpertiesUtil.getProperties(PorpertiesUtil.MYSQLNAME);
		/*System.out.println("driverName:" + driverName + ",url:" + url
				+ ",pass:" + pass + ",user:" + user);*/
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, pass);
			/*conn.setAutoCommit(false);
			conn.commit();
			conn.rollback();
			conn.setSavepoint();*/
		} catch (Exception e) {
			System.out.println("数据库连接失败!!!");
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		Connection conn =openDB();
		System.out.println(conn.toString());
	}

}

package com.java.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PorpertiesUtil {

	public static final String MYSQLNAME = "mysql.username";

	public static final String MYSQLPASS = "mysql.password";

	public static final String MYSQLURL = "mysql.url";

	public static final String MYSQLDRIVERNAME = "mysql.drivername";

	public static Properties p = new Properties();

	static {
		InputStream is = Object.class
				.getResourceAsStream("/config/db.properties");
		try {
			p.load(is);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * 
	 */
	public static String getProperties(String key) {
		if (key != null && !key.trim().equals("")) {
			return p.getProperty(key);
		}
		return null;
	}
}

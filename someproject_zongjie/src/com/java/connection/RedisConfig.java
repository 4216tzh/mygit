package com.java.connection;

import java.io.InputStream;
import java.util.Properties;

public class RedisConfig {
	public static Properties p = new Properties();
	static {
		InputStream inputStream = null;
		try {
			inputStream = RedisConfig.class.newInstance().getClass()
					.getClassLoader()
					.getResourceAsStream("config/redis.properties");
			p.load(inputStream);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}

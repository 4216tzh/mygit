package com.java.ioc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public static final String KEY_ONE="KEY";
	
	private static InputStream is;
	
	private static Properties p=new Properties();
	
	public static final String PACKAGE_NAME="PACKAGE_NAME";
	
	static{
		is=Object.class.getResourceAsStream("/config/db.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		if(key==null||key.trim().equals("")){
			return null;
		}
		return p.getProperty(key);
	}
	
	public static  void setValue(String key,String val){
		p.setProperty(key, val);
	}

}

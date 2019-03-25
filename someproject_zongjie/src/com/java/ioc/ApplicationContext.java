package com.java.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
	
	public static Map map=new ConcurrentHashMap();
	
	static{
		List<Class<?>> list=PathUtil.getClasses(PropertiesUtil.getValue(PropertiesUtil.PACKAGE_NAME));
		System.out.println(list.size());
		for(Class clazz:list){
			try {
				Object obj=clazz.newInstance();
				String test=clazz.getName();
				//String s=((Setter)clazz.getAnnotation(Setter.class)).description();
				map.put(test, obj);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static Object getBean(String key) throws Exception{
		Object obj=map.get(key);
		if(obj!=null){
			Class<?> clazz=obj.getClass();
			Field[] fields=clazz.getDeclaredFields();
			for(Field f:fields){
				Annotation annotation=f.getAnnotation(Setter.class);
				if(annotation!=null){
					String name="set"+f.getName().substring(1);
					Method m=clazz.getMethod(name, f.getType());
					m.invoke(obj, Class.forName(key));
				}
			}
		}
		return obj;
	}

}

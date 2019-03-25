package com.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.java.file.excel.Customer;

public class SomeReflect {
	
	public static void main(String[] args) {
		try{
		Class<?> clazz=Class.forName("com.java.file.excel.Customer");
		//创建一个实例对象
		Object obj=clazz.newInstance();
		Customer c=(Customer)obj;
		c.setAddress("address");
		System.out.println(obj.getClass().getSimpleName());
		//得到对应实例对象的声明所有属性(不管公有还是私有)
		Field[] fields= clazz.getDeclaredFields();
		for(Field f:fields){
		f.setAccessible(true);//设置为true,私有的属性也是可见的，外部是可以访问的
			System.out.println("属性名:"+f.getName()+",属性值："+f.get(c));
		}
		Method[] mmm=clazz.getDeclaredMethods();
		/*//
		Method[] methods=clazz.getMethods();
		for(Method m:methods){
			System.out.println("方法名:"+m.getName());
		}
		//根据方法名和参数类型得到对应的方法
		Method m=clazz.getMethod("setAddress", String.class);
		//传入一个实例对象和对应方法的参数值，执行对应的方法
		m.invoke(obj, "123");
		System.out.println(obj.toString());*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//test();
	}
	
	public static void test(){
		
			try {
				//通过类加载器获取car对象
				 ClassLoader loader=Thread.currentThread().getContextClassLoader();
				Class<?> clazz = loader.loadClass("com.java.file.excel.Customer");
				 //获取类的默认构造器对象并通过他实例化car
				 Constructor<?> cons= clazz.getDeclaredConstructor((Class[]) null);
				 Object obj= cons.newInstance();
				 //clazz.newInstance();
				 Method m=clazz.getMethod("setAddress", String.class);
				 m.invoke(obj, "中国北京10086111");
				 Customer c=(Customer)obj;
				 System.out.println("地址:"+c.getAddress());
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
			}
	         
	}
}

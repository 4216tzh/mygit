package com.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.java.file.excel.Customer;

public class SomeReflect {
	
	public static void main(String[] args) {
		try{
		Class<?> clazz=Class.forName("com.java.file.excel.Customer");
		//����һ��ʵ������
		Object obj=clazz.newInstance();
		Customer c=(Customer)obj;
		c.setAddress("address");
		System.out.println(obj.getClass().getSimpleName());
		//�õ���Ӧʵ�������������������(���ܹ��л���˽��)
		Field[] fields= clazz.getDeclaredFields();
		for(Field f:fields){
		f.setAccessible(true);//����Ϊtrue,˽�е�����Ҳ�ǿɼ��ģ��ⲿ�ǿ��Է��ʵ�
			System.out.println("������:"+f.getName()+",����ֵ��"+f.get(c));
		}
		Method[] mmm=clazz.getDeclaredMethods();
		/*//
		Method[] methods=clazz.getMethods();
		for(Method m:methods){
			System.out.println("������:"+m.getName());
		}
		//���ݷ������Ͳ������͵õ���Ӧ�ķ���
		Method m=clazz.getMethod("setAddress", String.class);
		//����һ��ʵ������Ͷ�Ӧ�����Ĳ���ֵ��ִ�ж�Ӧ�ķ���
		m.invoke(obj, "123");
		System.out.println(obj.toString());*/
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//test();
	}
	
	public static void test(){
		
			try {
				//ͨ�����������ȡcar����
				 ClassLoader loader=Thread.currentThread().getContextClassLoader();
				Class<?> clazz = loader.loadClass("com.java.file.excel.Customer");
				 //��ȡ���Ĭ�Ϲ���������ͨ����ʵ����car
				 Constructor<?> cons= clazz.getDeclaredConstructor((Class[]) null);
				 Object obj= cons.newInstance();
				 //clazz.newInstance();
				 Method m=clazz.getMethod("setAddress", String.class);
				 m.invoke(obj, "�й�����10086111");
				 Customer c=(Customer)obj;
				 System.out.println("��ַ:"+c.getAddress());
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
			}
	         
	}
}

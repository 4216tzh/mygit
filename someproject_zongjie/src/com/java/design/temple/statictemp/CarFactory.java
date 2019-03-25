package com.java.design.temple.statictemp;

/**
 * ���÷���õ���Ӧ�Ĳ�Ʒ����
 * @author tzh
 *
 */
public class CarFactory {
	
	@SuppressWarnings("unchecked")
	public static <T extends Car> T createCar(Class<T> flag){
		T result=null;
		try{
			result =(T)Class.forName(flag.getName()).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	

}

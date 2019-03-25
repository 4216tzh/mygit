package com.java.design.temple.statictemp;

/**
 * 利用反射得到对应的产品对象
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

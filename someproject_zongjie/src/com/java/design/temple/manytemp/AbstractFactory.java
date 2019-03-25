package com.java.design.temple.manytemp;

import com.java.design.temple.statictemp.Car;

/**
 * 多工厂模式，具体的产品工厂生产具体的产品
 * @author tzh
 *
 */
public interface AbstractFactory {
	
	public Car createCar();
	
	//public Car createSubway();
		
	

}

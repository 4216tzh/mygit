package com.java.design.temple.manytemp;

import com.java.design.temple.manytemp.Impl.BikeFactory;
import com.java.design.temple.manytemp.Impl.SubWayFactory;
import com.java.design.temple.statictemp.Car;
/**
 * 多工厂模式测试
 * @author tzh
 *
 */
public class ManyFacTest {
	
	public static void main(String[] args) {
		AbstractFactory a1=new BikeFactory();
		Car car=a1.createCar();
		car.desc();
		AbstractFactory a2=new SubWayFactory();
		Car car2=a2.createCar();
		car2.desc();
	}
}

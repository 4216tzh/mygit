package com.java.design.temple.statictemp;

import com.java.design.temple.statictemp.impl.Bike;

public class FactoryTest {
	
	public static void main(String[] args) {
		Car b=CarFactory.createCar(Bike.class);
		b.desc();
	}

}

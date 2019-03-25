package com.java.design.temple.manytemp.Impl;

import com.java.design.temple.manytemp.AbstractFactory;
import com.java.design.temple.statictemp.Car;
import com.java.design.temple.statictemp.impl.Bike;

public class BikeFactory implements AbstractFactory {

	@Override
	public Car createCar() {
		return new Bike();
	}

	/*@Override
	public Car createSubway() {
		return new Subway();
	}*/

}

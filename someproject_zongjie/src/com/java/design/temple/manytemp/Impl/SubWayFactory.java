package com.java.design.temple.manytemp.Impl;

import com.java.design.temple.manytemp.AbstractFactory;
import com.java.design.temple.statictemp.Car;
import com.java.design.temple.statictemp.impl.Subway;

public class SubWayFactory implements AbstractFactory{

	@Override
	public Car createCar() {
		return new Subway();
	}

	/*@Override
	public Car createSubway() {
		return null;
	}*/

}

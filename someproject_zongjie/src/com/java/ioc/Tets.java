package com.java.ioc;

import com.java.ioc.Impl.ReflectServiceImpl;

public class Tets {
	
	public static void main(String[] args) throws Exception {
		ReflectService r=(ReflectServiceImpl)ApplicationContext.getBean(ReflectServiceImpl.class.getName());
		r.print();
	}

}

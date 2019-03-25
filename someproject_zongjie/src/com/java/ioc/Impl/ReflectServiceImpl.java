package com.java.ioc.Impl;

import com.java.ioc.ReflectDao;
import com.java.ioc.ReflectService;
import com.java.ioc.Setter;

@Setter
public class ReflectServiceImpl implements ReflectService{
	
	@Setter
	private ReflectDao reflectDao;

	@Override
	public void some() {
		System.out.println(reflectDao.some());
	}

	@Override
	public void print() {
		System.out.println(reflectDao.print());
	}

	public ReflectDao getReflectDao() {
		return reflectDao;
	}

	public void setReflectDao(ReflectDao reflectDao) {
		this.reflectDao = reflectDao;
	}
	
}

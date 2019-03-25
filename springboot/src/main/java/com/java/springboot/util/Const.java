package com.java.springboot.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.BeanDefinitionDocumentReader;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;


/**
 * 一些常量
 * @author tzh
 *
 */
public class Const {
	
	public static final String USER="user";
	
	public static final String ATTR="name";
	
	private final String test;
	
	public Const(String test){
		this.test=test;
	}
	
	
	public static void main(String[] args) {
		BeanDefinitionDocumentReader bdd;
		AbstractBeanFactory abf;
		BeanFactory b;
		ApplicationContext a;
		ConfigurableBeanFactory cb=new DefaultListableBeanFactory();
		WebApplicationContext wac;
		BlockingQueue<String> queue=new LinkedBlockingQueue<String>();
		new ArrayBlockingQueue<String>(100);
	}


	public String getTest() {
		return test;
	}
	

}

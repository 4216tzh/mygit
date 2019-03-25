package com.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.java.design.single.SingleModel;

public class SomeTest {
	
	public transient  String name;
	
	public static void main(String[] args) {
		/*SingleModel s=SingleModel.getInstance();
		s.setFlag("frist");
		System.out.println("第一次:"+s.getFlag());
		SingleModel s1=SingleModel.getInstance();
		System.out.println("第二次:"+s1.getFlag());
		Executors.newFixedThreadPool(1);*/
		/**
		 * 给字符串数组的每个相邻的字符串之间加入第一个参数的内容
		 * 下面的值:“abd我mkl我vswq”
		 */
		System.out.println(String.join("我", "abd","mkl","vswq"));
		
		
	}
	
	public static  boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();   
	}

}

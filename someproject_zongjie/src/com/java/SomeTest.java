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
		System.out.println("��һ��:"+s.getFlag());
		SingleModel s1=SingleModel.getInstance();
		System.out.println("�ڶ���:"+s1.getFlag());
		Executors.newFixedThreadPool(1);*/
		/**
		 * ���ַ��������ÿ�����ڵ��ַ���֮������һ������������
		 * �����ֵ:��abd��mkl��vswq��
		 */
		System.out.println(String.join("��", "abd","mkl","vswq"));
		
		
	}
	
	public static  boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();   
	}

}

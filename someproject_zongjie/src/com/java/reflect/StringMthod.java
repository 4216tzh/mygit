package com.java.reflect;

import java.util.ArrayList;
import java.util.List;

public class StringMthod {
	
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("1234");
		String[] s=list.toArray(new String[0]);
		System.out.println(s.length);
		//System.arraycopy(src, srcPos, dest, destPos, length);//Êý×é¸´ÖÆ
		StringBuffer sb=new StringBuffer();
		sb.append(123);
		sb.append("q123q");
		String sq="qwe123asd";
		String ss=String.format("%shhh", sq);
		String sss=String.format("%d%d%d", 123,456,678);
		System.out.println("sss:"+sss);
		System.out.println(ss);
		String x="1.xlsx";
		Boolean b=x.toLowerCase().endsWith("xlsx");
		System.out.println("b:"+b);
		String s1=x.substring(x.lastIndexOf(".")+1,x.length());
		System.out.println("s1:"+s1);
	}

	
}

package com.java;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class People {
	
	private String bizNo;

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	 
	public static void main(String[] args) {
		long x=1551774746389l;
		long y=1551774777001l;
		System.out.println(x-y);
		String str="1,,2";
		if(str.endsWith(",")) {
			System.out.println("yes");
			System.out.println(str.substring(0, str.length()-1));
			System.out.println(str);
		}
		String[] array=str.split(",");
		String result="";
		int m=array.length;
		for(int i=0;i<array.length;i++) {
			String s=array[i];
			if("".equals(s)) {
				continue;
			}else {
				if(i==(m-1)) {
					result+=s;
				}else {
					result+=s+",";
				}
			}
		}
		System.out.println(result);
		/*for(String obj:array) {
			if("".equals(obj)) {
				System.out.println("yes");
			}
			System.out.println(obj.toString());
		}*/
	}
	
}

package com.java;

import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.java.file.excel.Customer;
import com.java.test.School;

public class ForTest {
	
	private volatile Customer c=new Customer();

	private volatile int age=0;

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>();
		list.add(5);
		list.add(4);
		list.add(7);
		list.add(3);
		list.add(15);
		list.add(2);
		//Collections.sort(list);
		Object[] xxx=list.toArray();
		Arrays.sort(xxx);
		for(Object x:Arrays.asList(xxx)) {
			System.out.print(x+",");
		}
		String str="ewtrewyr";
		int x=str.compareTo("yruivfhdsfvhs");
		System.out.println(x);
		List<School> collec=new ArrayList<School>();
		//ʹ��lamda���ʽ����
		collec.sort((School s1,School s2)->s1.getName().compareTo(s2.getName()));
		collec.sort((s1,s2)->s1.getName().compareTo(s2.getName()));
		//ʹ��lamda���ʽ����(����������)
		collec.sort((s1,s2)->{
			if(s1.getName().equals(s2.getName())) {
				return s1.getAddress().compareTo(s2.getAddress());
			}else {
				return s1.getName().compareTo(s2.getName());
			}
		});
		//����������
		collec.sort(Comparator.comparing(School::getName).thenComparing(School::getAddress));
		
		//
		Collections.sort(collec,Comparator.comparing(School::getName).thenComparing(School::getAddress));
		
		
	}
	
	
}

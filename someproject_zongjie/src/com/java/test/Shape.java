package com.java.test;

public class Shape {
	
	private String name;
	
	private String size;
	
	public void print(Shape s){
		System.out.println(s.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	

}

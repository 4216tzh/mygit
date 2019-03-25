package com.java.test;

public class Cricle extends Shape{

	private Double len;
	
	private Double sor;

	public Double getLen() {
		return len;
	}

	public void setLen(Double len) {
		this.len = len;
	}

	public Double getSor() {
		return sor;
	}

	public void setSor(Double sor) {
		this.sor = sor;
	}
	
	public static void main(String[] args) {
		Cricle c=new Cricle();
		c.setName("test");
		c.print(c);
		
	}
	
}

package com.java.springboot.quartz.enums;

public enum JobTime {
	
	TEN_SECOND(1,"");
	
	private int vlaue;
	
	private String txt;

	private JobTime(int vlaue, String txt) {
		this.vlaue = vlaue;
		this.txt = txt;
	}

	public int getVlaue() {
		return vlaue;
	}

	public void setVlaue(int vlaue) {
		this.vlaue = vlaue;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	
	

}

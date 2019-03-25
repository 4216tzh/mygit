package com.java.springboot.quartz.enums;

import org.quartz.Job;

import com.java.springboot.quartz.FriSchedule;

public enum JobType {
	
	test(1,"简单的一个测试",FriSchedule.class,false);
	
	private Integer value;
	
	private String txt;
	
	private Class<? extends Job> className;
	
	private Boolean isSystem;
	
	private JobType(int value,String txt,Class<? extends Job> className,boolean IsSystemJob){
		this.value = value;
		this.txt = txt;
		this.className = className;
		this.isSystem = IsSystemJob;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Class<? extends Job> getClassName() {
		return className;
	}

	public void setClassName(Class<? extends Job> className) {
		this.className = className;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}
	
	

}

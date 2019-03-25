package com.java.annonation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StuModel implements Serializable{
	
	/**
	 * 
	 *//*
	private static final long serialVersionUID = 4706445592459596104L;*/

	@AutoWire(description="id")
	private String id;
	
	@AutoWire(description="名字")
	private String name;
	
	@AutoWire(description="性别")
	private String sex;
	
	@AutoWire(description="专业")
	private String major;
	
	@AutoWire(description="出生日期")
	private Date birthday;

	@AutoWire(description="年龄")
	private Integer age;
	
	@AutoWire(description="余额")
	private BigDecimal money;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "StuModel [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", major=" + major + ", birthday=" + birthday + ", age="
				+ age + ", money=" + money + "]";
	}
	
}

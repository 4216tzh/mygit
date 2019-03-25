package com.java.file.excel;

import java.util.Comparator;

/**
 * Comparator排序逻辑在另一个实现
 * Comparable 自然排序
 * @author tzh
 *
 */
public class Customer implements Cloneable,Comparator<Customer>,Comparable<Customer>{

	public static final String PATH = "H:\\excel\\";

	private String id;

	private String name;

	private String address;

	private String account;

	private String tel;

	private String sex;

	private Integer age;

	private String hoppy;

	private String remark;

	private Boolean isVip;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHoppy() {
		return hoppy;
	}

	public void setHoppy(String hoppy) {
		this.hoppy = hoppy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsVip() {
		return isVip;
	}

	public void setIsVip(Boolean isVip) {
		this.isVip = isVip;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address
				+ ", account=" + account + ", tel=" + tel + ", sex=" + sex
				+ ", age=" + age + ", hoppy=" + hoppy + ", remark=" + remark
				+ ", isVip=" + isVip + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}


	@Override
	public int compare(Customer o1, Customer o2) {
		if(o1.getAccount().equals(o2.getAccount())) {
			
		}
		return 0;
	}

	@Override
	public int compareTo(Customer o) {
		int res=this.getAccount().compareTo(o.getAccount());
		if(res!=0) {
			int compare=this.getName().compareTo(o.getName());
			return compare;
		}else {
			return res;
		}
	}
	
	

}

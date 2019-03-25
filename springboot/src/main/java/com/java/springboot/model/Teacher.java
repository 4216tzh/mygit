package com.java.springboot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_TEACHER")
public class Teacher implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3355087660903176728L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="telphone",nullable=false,unique=true)
	private String telphone;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="birthday")
	private Date  birthday;
	
	@Column(name="work")
	private String work;
	
	@ManyToMany
	@JoinTable(name="TBL_STUDENT_TEACHER",joinColumns={@JoinColumn(name="teaId")},inverseJoinColumns={@JoinColumn(name="stuId")})
	private List<Student> stuList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}
	
	public List<Student> getStuList() {
		return stuList;
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", address=" + address
				+ ", telphone=" + telphone + ", sex=" + sex + ", age=" + age
				+ ", birthday=" + birthday + ", work=" + work + "]";
	}
	
}

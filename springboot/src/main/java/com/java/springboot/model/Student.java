package com.java.springboot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"stuNum"})},name="TBL_STUDENT")
public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="birthDay")
	private Date birthDay;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="stuNum",unique=true)
	private String stuNum;
	
	@Column(name="major",nullable=false)
	private String major;
	
	//@Transient表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性
	@Transient
	private Map<String, Object> map;
	
	@ManyToMany
	@JoinTable(name="TBL_STUDENT_TEACHER",joinColumns={@JoinColumn(name="stuId")},inverseJoinColumns={@JoinColumn(name="teaId")})
	private List<Teacher> teaList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public List<Teacher> getTeaList() {
		return teaList;
	}

	public void setTeaList(List<Teacher> teaList) {
		this.teaList = teaList;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", birthDay=" + birthDay + ", name="
				+ name + ", StuNum=" + stuNum + ", major=" + major + "]";
	}
	

}

package com.java.springboot.service;

import java.util.List;
import java.util.Map;

import com.java.springboot.model.Student;


public interface StudentService {
	
	
	public Student findOne(Long id);
	
	public void delete(Long id);
	 
	public void test();
	
	public Student save(Student student);
	
	public Student findBystuNum(String stuNum);
	
	public List<Student> query(Map<String, Object> map);
	
	public void deleteBystuNum(String stuNum);

}

package com.java.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.java.springboot.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long>,JpaSpecificationExecutor<Teacher>{
	
	public Teacher findByTelphone(String tel);
	
	public Teacher findById(Long id);
	
}

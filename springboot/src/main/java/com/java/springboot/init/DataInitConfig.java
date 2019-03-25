package com.java.springboot.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.java.springboot.service.StudentService;

//@Component
@Configuration
@Profile({"dev"})//{"dev","pro"}
public class DataInitConfig implements CommandLineRunner {
	
	@Autowired
	private StudentService studentService;

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("common test begin");
		studentService.test();
		System.out.println("common test end");
	}


}

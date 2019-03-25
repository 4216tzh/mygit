package com.java.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {
	
	@RequestMapping(value="/one",method= {RequestMethod.GET})
	public ResponseEntity<String>  nameOne() {
		
		return ResponseEntity.ok().body("我就是我");
		
	}

}

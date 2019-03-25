package com.java.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.model.User;

@RestController
public class UserResetController {
	
	
	@RequestMapping("/reset")
	public User index(@RequestParam(required=true,defaultValue="test") String name){
		return new User("男", name);
	}
	
	/*@RequestMapping(value="/start",method={RequestMethod.POST,RequestMethod.GET},produces = "application/json; charset=UTF-8")
	public String test() {
		return "test reset";
	}*/

}

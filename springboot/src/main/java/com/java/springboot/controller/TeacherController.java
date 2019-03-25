package com.java.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/teacher")
public class TeacherController {
	
	@RequestMapping(value="/index")
	public String index(){
		return "teacher";
	}
	
	@RequestMapping(value="/insert")
	@ResponseBody
	public String insert(@RequestParam(name="name")String name,@RequestParam(name="pass")String pass){
		
		return "success";
	}
	
	@RequestMapping(value="/start",method={RequestMethod.POST,RequestMethod.GET},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String test() {
		JSONObject json=new JSONObject();
		json.put("key", "value");
		json.put("result", "success");
		return json.toJSONString();
	}

}

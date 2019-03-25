package com.java.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.java.springboot.aop.LoggerManage;
import com.java.springboot.listener.ListenerEventService;
import com.java.springboot.redis.JedisPoolUtils;
import com.java.springboot.service.StudentService;

@Controller
@RequestMapping("/fri")
public class TestController {
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private JedisPoolUtils jedisPoolUtils;
	
	@Autowired
	private ListenerEventService listenerEventService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@LoggerManage(descrption="测试页面")
	@RequestMapping(value="/test")
	public String index(){
		ValueOperations v=redisTemplate.opsForValue();
		v.set("test", "value");
		Object obj=v.get("test");
		System.out.println(obj.toString());
		listenerEventService.register("哈哈哈_This is test");
		return "test";
	}
	
	@LoggerManage(descrption="redis测试")
	@RequestMapping(value="/redis")
	@ResponseBody
	public String redis(HttpServletRequest request, HttpServletResponse response){
		
		Jedis jedis=null;
		try{
			jedis=jedisPoolUtils.getJedis();
			jedis.set("test", "jedis");
			Map<String, String> map=new HashMap<String, String>();
			map.put("name", "mike");
			map.put("sex", "bob");
			map.put("age", "22");
			jedis.hmset("map", map);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				jedisPoolUtils.returnRes(jedis);
			}
		}
		return "test one";
	}
	
	
	
	
}

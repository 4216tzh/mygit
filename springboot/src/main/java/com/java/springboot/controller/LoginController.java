package com.java.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;

import com.java.springboot.aop.AroundManage;
import com.java.springboot.aop.LoggerManage;
import com.java.springboot.dao.StudentRepository;
import com.java.springboot.dao.TeacherRepository;
import com.java.springboot.model.Student;
import com.java.springboot.model.Teacher;
import com.java.springboot.redis.JedisPoolUtils;
import com.java.springboot.service.StudentService;
import com.java.springboot.util.Const;

@Controller
@RequestMapping(value="/")
public class LoginController {
	
	@Autowired
	@Qualifier("StudentServiceImpl")
	private StudentService studentService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private JedisPoolUtils pool;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping(value="/login")
	public String index(){
		return "/nologin";
	}
	
	@LoggerManage(descrption="登录")
	@RequestMapping(value="/logins")
	public String interceptor(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession(true);
		session.setAttribute(Const.ATTR, Const.USER);
		Object.class.getResourceAsStream("");
		db();
		redis();
		return "/test";
	}
	
	@AroundManage(description="环绕通知")
	@RequestMapping(value="/loginout")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(true);
		session.removeAttribute(Const.ATTR);
		return "nologin";
	}
	
	private void redis(){
		Jedis jedis=null;
		try{
			ValueOperations ops=redisTemplate.opsForValue();
			String key=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			Boolean b=ops.setIfAbsent(key, "value");
			if(b) {
				redisTemplate.expire(key, 20, TimeUnit.SECONDS);
			}
			jedis=pool.getJedis();
			jedis.set("我就是我", "value");
			
			Map<String, String> map=new HashMap<String, String>();
			map.put("age", "22");
			map.put("birthDay", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			map.put("address", "上海市漕河泾开发区");
			jedis.hmset(Const.ATTR, map);
			redisTemplate.execute(new RedisCallback<Object>() {

				@Override
				public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
					return null;
				}
			});
			//jedis.set
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				pool.returnRes(jedis);
			}
		}
	}

	private void db(){
		Student stu=new Student();
		stu.setBirthDay(new Date());
		stu.setMajor("techongly");
		stu.setName("mike");
		stu.setStuNum(UUID.randomUUID().toString().replace("-", ""));
		
		//List<Student> list=new ArrayList<Student>(studentRepository.findAll());
		for(int i=0;i<0;i++){
			Teacher t=new Teacher();
			t.setAddress("上海");
			t.setAge(22);
			t.setBirthday(new Date());
			t.setName("bob");
			t.setSex("男");
			//t.setStuList(list);
			t.setTelphone(UUID.randomUUID().toString().replace("-", ""));
			t.setWork("math");
			teacherRepository.save(t);
			Teacher te=teacherRepository.findByTelphone(t.getTelphone());
		}
		
		stu.setTeaList(teacherRepository.findAll());
		Student res=studentService.findBystuNum(studentService.save(stu).getStuNum());
		System.out.println(res.toString());
		//System.out.println(te.toString());
	}
}

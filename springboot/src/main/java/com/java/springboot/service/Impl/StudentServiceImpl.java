package com.java.springboot.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.springboot.dao.StudentRepository;
import com.java.springboot.model.Student;
import com.java.springboot.service.StudentService;

@Service
@CacheConfig(cacheNames="student")
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private Session session;
	
	@Override
	public void test() {
		/*//加载配置文件
        Configuration config = new Configuration().configure();
        //根据配置文件创建会话工厂
        SessionFactory factory = config.buildSessionFactory();
        //根据会话工厂创建会话
        Session session = factory.getCurrentSession();*/
        //创建一个事物对象
        Transaction tx = session.beginTransaction();
        //new 一个学生对象
       // Student student = new Student("张三",19,99);
        Student student=new Student();
        student.setBirthDay(new Date());
        student.setName("bob");
        //将对象持久化到数据表中
        session.save(student);
        //提交事务
        tx.commit();
        //关闭会话
        session.close();
        //关闭工厂
        //factory.close();
	}

	
	@Override
	public Student findOne(Long id) {
		return studentRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		studentRepository.delete(id);
	}

	@Transactional
	@CachePut(key="'StuNum'+#p0.stuNum")
	@Override
	public Student save(Student student) {
		Student s=studentRepository.save(student);
		return s;
	}

	@Cacheable(key="'StuNum'+#p0")
	@Override
	public Student findBystuNum(String stuNum) {
		return studentRepository.findByStuNum(stuNum);
	}
	
	

	@Override
	public List<Student> query(Map<String, Object> map) {
		/*Sort sort=new Sort(Direction.DESC, "name");
		Pageable p=new PageRequest(1, 15, sort);
		studentRepository.findAll(null, p);*/
		return null;
	}

	@Transactional
	@CacheEvict(key="'StuNum'+#p0")
	@Override
	public void deleteBystuNum(String stuNum) {
		studentRepository.deleteByStuNum(stuNum);
	}

}

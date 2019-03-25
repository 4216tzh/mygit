package com.java.springboot.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.java.springboot.model.Student;
/**
 * 自定义方法更新和删除数据要加上@Transactional注解
 * 保证事务的完整性,这是spring data jpa 的规范
 * @author tzh
 *
 */
public interface StudentRepository extends JpaRepository<Student, Long>,JpaSpecificationExecutor<Student>{
	
	public Student findByStuNum(String stuNum);
	
	@Transactional
	public void deleteByStuNum(String stuNum);
	
	@Query("select * from TBL_STUDENT where name=?1 and major=?2")
	public List<Student> getByCondition(String name,String major);
	
	@Query(value="select * from TBL_STUDENT where name=:name and major=:major ",nativeQuery=true)
	public List<Student> getByTest(@Param("name") String name,@Param("major") String major);
	
	@Query(value="select * from TBL_STUDENT where name like :name and birthDay >:birthDay ",nativeQuery=true)
	public List<Student> getByDate(@Param("name") String name,@Param("birthDay") String birthDay);
	
	/**
	 * nativeQuery,本地查询也就是使用原生sql语句查询
	 * //@Query(value = "select * from #{#entityName} where stuNum = ?1 ORDER BY ?#{#pageable}", nativeQuery = true)
	 *  '#{#entityName}'值为'Student'对象对应的数据表名称(Student)
	 *  @Entity(name=""),name对应的值
	 */
	@Query(value = "select * from TBL_STUDENT where stuNum = ?1 ORDER BY ?#{#pageable}", nativeQuery = true)
	public List<Student> getByStuNum(String stuNum,Pageable pageable);
	
	
	
	/***多表查询得到结果****/
	/*@Query(value="select a.* from sys_user a left join sys_data_type b on a.sys_data_type=b.id where b.name= ?1",nativeQuery=true)
	List<Student> findByCondition(String name);*/
	
}

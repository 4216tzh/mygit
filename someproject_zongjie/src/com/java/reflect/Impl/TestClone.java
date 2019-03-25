package com.java.reflect.Impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.java.file.excel.Customer;
/**
 * 克隆方法
 * @author tzh
 *
 */
public class TestClone implements Cloneable{
	private String name;
	
	private Integer age;
	
	private Date birthDay;
	
	private Customer customer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
	@Override
	public String toString() {
		return "TestClone [name=" + name + ", age=" + age + ", birthDay="
				+ birthDay + ", customer=" + customer.toString() + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		ByteArrayOutputStream byteOut = null;   
        ObjectOutputStream objOut = null;   
        ByteArrayInputStream byteIn = null;   
        ObjectInputStream objIn = null;   
           
        try {   
        	InputStream is=new FileInputStream(new File(""));
        	DataInputStream dis=new DataInputStream(new BufferedInputStream(new FileInputStream("")));
        	InputStream iss=new ByteArrayInputStream(new String().getBytes("utf-8"));
// 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
            byteOut = new ByteArrayOutputStream();    
            objOut = new ObjectOutputStream(byteOut);    
            objOut.writeObject(this);   
  // 将流序列化成对象
            byteIn = new ByteArrayInputStream(byteOut.toByteArray());   
            objIn = new ObjectInputStream(byteIn);   
               
            return (TestClone) objIn.readObject();   
        } catch (IOException e) {   
            throw new RuntimeException("Clone Object failed in IO.",e);      
        } catch (ClassNotFoundException e) {   
            throw new RuntimeException("Class not found.",e);      
        } finally{   
            try{   
                byteIn = null;   
                byteOut = null;   
                if(objOut != null) objOut.close();      
                if(objIn != null) objIn.close();      
            }catch(IOException e){      
            }      
        }   
    }   
		
	
	
	public static void main(String[] args) throws Exception {
		Customer c=new Customer();
		c.setAccount("test one");
		c.setAge(18);
		TestClone t=new TestClone();
		t.setCustomer(c);
		t.setAge(999);
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		t.setBirthDay(df.parse("1995-10-10"));
		t.setName("lily");
		TestClone t1=(TestClone)t.clone();
		System.out.println(t1.toString());
		t1.getCustomer().setAge(22);
		System.out.println("t.age:"+t.getCustomer().getAge());
		
	}
	
}

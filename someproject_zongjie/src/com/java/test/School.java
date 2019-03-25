package com.java.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import com.java.annonation.StuModel;

/**
 * ���л�ʵ����ȿ�¡�������д��������������ԣ�
 * ��ô����������Ӧʵ�����л�(ʵ��  Serializable �ӿ�)
 * 
 * @author tzh
 *
 */
public class School implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6561146668873739449L;

	private String id;

	private String name;

	private String address;

	private Date date;

	private StuModel stuModel;

	public School() {
	}

	public School(String id, String name, String address, Date date,
			StuModel stuModel) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.date = date;
		this.stuModel = stuModel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StuModel getStuModel() {
		return stuModel;
	}

	public void setStuModel(StuModel stuModel) {
		this.stuModel = stuModel;
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", address=" + address
				+ ", date=" + date + ", stuModel=" + stuModel.toString() + "]";
	}

	@Override
	protected Object clone(){
		Object objB = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// ������д�뵽�ֽ�������
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		try {
			// ���ֽ������ж�ȡ����
			ObjectInputStream ois = new ObjectInputStream(bais);
			objB = (Object) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objB;
	}
	
	public static void main(String[] args){
		StuModel stu=new StuModel();
		stu.setAge(11);
		stu.setName("bob");
		School school=new School();
		school.setStuModel(stu);
		School s=(School)school.clone();
		StuModel sm=s.getStuModel();
		sm.setAge(22);
		sm.setName("tom");
		System.out.println(school.getStuModel().toString());
		System.out.println("��¡����:"+s.getStuModel().toString());
	}

}

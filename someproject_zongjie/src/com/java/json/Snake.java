package com.java.json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.java.Data;
import com.java.Datas;
import com.java.People;

public class Snake {
	
	private String remark2;

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	public static void main(String[] args) {
		Datas obj=new Datas();
		List<Object> list=new ArrayList<Object>();
		obj.setBizType("qwee");
		obj.setName("bob");
		obj.setOperationType("hahhaha");
		obj.setSerialNo("ewrewfsf");
		Data data=new Data();
		data.setOtherNo("123");
		People p=new People();
		p.setBizNo("987");
		Cat c=new Cat();
		c.setOperationRet("test");
		Dog d=new Dog();
		d.setRemark1("remarl");
		Snake s=new Snake();
		s.setRemark2("balnk");
		list.add(data);
		list.add(p);
		list.add(c);
		list.add(d);
		list.add(s);
		obj.setBizList(list);
		String str=JSONObject.toJSONString(obj);
		System.out.println(str);
				
	}

}

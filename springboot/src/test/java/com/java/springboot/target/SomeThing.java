package com.java.springboot.target;

import org.json.JSONArray;
import org.json.JSONObject;

public class SomeThing {
	
	public static void main(String[] args) {
		JSONObject json=new JSONObject();
		try {
			json.put("one", "我就是");
			json.put("two", true);
			json.put("three", 1);
			System.out.println(json.toString());
			JSONArray ja=new JSONArray();
			ja.put("111");
			ja.put(false);
			ja.put(111);
			System.out.println(ja.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}

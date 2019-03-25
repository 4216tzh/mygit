package com.java.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * jsonArrayµÄÒ»Ð©ÓÃ·¨
 * @author tzh
 *
 */
public class JsArray {
	
		private static final String KEY_ONE="Animail";
		private static final String KEY_TWO="Tree";
		private static final String KEY_THREE="Human";
	
		public static void main(String[] args) {
			
			//useNetJsonObject();
			useAliJsonObject();
		}
		
		private static void useNetJsonObject() {
			JSONArray array=new JSONArray();
			for(int x=0;x<3;x++) {
				JSONObject json=new JSONObject();
				json.put(KEY_ONE, "cat"+x);
				json.put(KEY_TWO, "Ð¡°×Ñî"+x);
				json.put(KEY_THREE, "chinese"+x);
				array.add(json);
			}
			JSONObject obj=new JSONObject();
			obj.put("name", "bob");
			obj.put("age", "22");
			obj.put("like", array);
			obj.put("sex", "ÄÐ");
			System.out.println("json×Ö·û´®:"+obj);
		}
		
		private static void useAliJsonObject() {
			com.alibaba.fastjson.JSONArray array=new com.alibaba.fastjson.JSONArray();
			for(int x=0;x<3;x++) {
				com.alibaba.fastjson.JSONObject json=new com.alibaba.fastjson.JSONObject();
				json.put(KEY_ONE, "cat"+x);
				json.put(KEY_TWO, "Ð¡°×Ñî"+x);
				json.put(KEY_THREE, "chinese"+x);
				array.add(json);
			}
			com.alibaba.fastjson.JSONObject obj=new com.alibaba.fastjson.JSONObject();
			obj.put("name", "bob");
			obj.put("age", "22");
			obj.put("like", array);
			obj.put("sex", "ÄÐ");
			System.out.println("json×Ö·û´®:"+obj);
			com.alibaba.fastjson.JSONArray jsons=(com.alibaba.fastjson.JSONArray)obj.get("like");
			for(int i=0;i<jsons.size();i++) {
				com.alibaba.fastjson.JSONObject target=(com.alibaba.fastjson.JSONObject)jsons.get(i);
				System.out.println(target.getString(KEY_ONE));
			}
		}

}

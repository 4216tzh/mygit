package com.java.connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisTest {
	
	public static void main(String[] args) {
		Jedis redis= JedisPoolUtils.getJedis();
		//string
		redis.set("key", "value");
		String value=redis.get("key");
		//map
		if(!"".equals(value)) {
			System.out.println("value:"+value);
		}
		Map<String, String> map=new HashMap<String,String>();
		map.put("name", "bob");
		map.put("age", "22");
		redis.hmset("map", map);
		List<String> list=redis.hmget("map", "age");
		if(list!=null&&list.size()>0) {
			for(String str:list) {
				System.out.println("str:"+str);
			}
		}
		//����
		redis.lpush("list", "valueOne");
		redis.lpush("list", "valueTwo");
		redis.lpush("list", "valueThree");
		redis.lpush("list", "valueFour");
		redis.lpush("list", "valueFive");
		redis.lpush("list", "valueSix");
		redis.lpush("list", "valueSeven");
		redis.lpush("list", "valueEight");
		
		List<String> result=redis.lrange("list", 0, -1);
		System.out.println(result!=null?result.size():"0");
		System.out.println("���У���һ��:"+redis.rpop("list"));
		System.out.println("���У��ڶ���:"+redis.rpop("list"));
		
		//����
		redis.sadd("set", "addSet");
		redis.sadd("set", "addSetOne");
		redis.sadd("set", "addSet");
		Set<String> set=redis.smembers("set");
		//ɾ�������е�ָ��ֵ
		Long l=redis.srem("set", "addSet");
		System.out.println("�ж�addSetOne�Ƿ��ڼ���sets�У�"+redis.sismember("set", "addSetOne"));
		
		//���򼯺�
		redis.zadd("zset", 7.0, "element001");
		
		//hash
		redis.hset("hset", "hkey", "values");
		String hStr=redis.hget("hset", "hkey");
		System.out.println("hash:"+hStr);
		
	}

}

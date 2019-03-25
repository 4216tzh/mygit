package com.java.springboot.redis;

import com.java.springboot.util.Const;

import redis.clients.jedis.Jedis;

public class TestRedisListener {
	
	public static void main(String[] args) {
		Jedis jedis=null;
		try{
			jedis=new Jedis("127.0.0.1", 6379);
			jedis.set(Const.ATTR, "Const.ATTR");
			//jedis.subscribe(new RedisListener(), Const.ATTR);
			//发布消息
			jedis.publish(Const.ATTR, "test");
			//Thread.sleep(1000);
			jedis.publish(Const.ATTR, "test1");
			//Thread.sleep(1000);
			jedis.publish(Const.ATTR, "test2");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(jedis!=null){
				jedis.close();
			}
		}
	}

}

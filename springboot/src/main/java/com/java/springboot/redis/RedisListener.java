package com.java.springboot.redis;

import org.springframework.stereotype.Component;

import com.java.springboot.util.Const;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

@Component
public class RedisListener extends JedisPubSub{

	@Override
	public void onMessage(String channel, String message) {
		super.onMessage(channel, message);
		System.out.println("监听通道>>>"+channel+",通道消息>>>>"+message);
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Jedis jedis=new Jedis("127.0.0.1", 6379);
		//监听channel通道的消息  
		jedis.subscribe(new RedisListener(), Const.ATTR);
	}
	
	

}

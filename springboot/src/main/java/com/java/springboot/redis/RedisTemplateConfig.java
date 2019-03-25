package com.java.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * redis配置,防止redis中存储的值出现乱码
 * @author tzh
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
@Configuration
public class RedisTemplateConfig {
	
	@Autowired
	private RedisTemplate redisTemplate;

	
	@Bean
	public RedisTemplate redisConfig(){
		//设置序列化Key的实例化对象
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		 //设置序列化Value的实例化对象
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}
}

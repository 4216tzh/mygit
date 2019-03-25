package com.java.springboot.redis;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix="spring.redis")
//@PropertySource(ignoreResourceNotFound=false,value ="file:conf/application.propertites" )
public class ApplicationConfig {
	
	@NotBlank
	private String redishost;
	
	@NotBlank
	private String redisport;
	
	private String redispassword;

	public String getRedishost() {
		return redishost;
	}

	public void setRedishost(String redishost) {
		this.redishost = redishost;
	}

	public String getRedisport() {
		return redisport;
	}

	public void setRedisport(String redisport) {
		this.redisport = redisport;
	}

	public String getRedispassword() {
		return redispassword;
	}

	public void setRedispassword(String redispassword) {
		this.redispassword = redispassword;
	}
	
	
	
}

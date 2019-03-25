package com.java.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Jedis帮助类
 * 
 * @author tzh
 *
 */
@Component
public class JedisPoolUtils {

	/**
	 * jedis连接池
	 */
	private JedisPool pool;

	@Autowired
	private ApplicationConfig applicationConfig;

	/**
	 * 获取redis连接密码
	 */
	// @Value("${spring.redispassword}")
	// public String AUTH=applicationConfig.getRedispassword();

	/**
	 * 建立连接池
	 * 
	 */
	private void createJedisPool() {

		// 建立连接池配置参数
		JedisPoolConfig config = new JedisPoolConfig();

		// 设置最大连接数
		config.setMaxTotal(1024);

		// 设置最大阻塞时间，记住是毫秒数millisecondss
		config.setMaxWaitMillis(10000);

		// 设置空间连接
		config.setMaxIdle(300);

		config.setTestOnBorrow(false);
		String auth = applicationConfig.getRedispassword();
		// 创建连接池
		if (StringUtils.isEmpty(auth)) {
			pool = new JedisPool(config, applicationConfig.getRedishost(), Integer.valueOf(applicationConfig.getRedisport()), 10000);
		} else {
			pool = new JedisPool(config, applicationConfig.getRedishost(), Integer.valueOf(applicationConfig.getRedisport()), 10000, auth);
		}

	}

	/**
	 * 在多线程环境同步初始化
	 */
	public synchronized void poolInit() {
		if (pool == null)
			createJedisPool();
	}

	/**
	 * 获取一个jedis 对象
	 * 
	 * @return
	 */
	public Jedis getJedis() {

		if (pool == null)
			poolInit();
		return pool.getResource();
	}

	/**
	 * 归还一个连接
	 * 
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public void returnRes(Jedis jedis) {
		pool.returnResource(jedis);
	}

}

package com.java.connection;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedisPoolUtils {

	private static JedisPool pool;

	public static final String AUTH = RedisConfig.p
			.getProperty("ouji.redis.password");

	// public static final String AUTH = "";

	/**
	 * 建立连接池 真实环境，一般把配置参数缺抽取出来。
	 * 
	 */
	private static void createJedisPool() {

		// 建立连接池配置参数
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(300);

		// 设置最大阻塞时间，记住是毫秒数millisecondss
		config.setMaxWaitMillis(10000);

		// 设置空间连接
		config.setMaxIdle(30);

		config.setTestOnBorrow(true);

		// 创建连接池
		if (AUTH==null||AUTH.trim().equals("")) {
			pool = new JedisPool(config,
					RedisConfig.p.getProperty("creditcf.redis.ip"),
					Integer.valueOf(RedisConfig.p
							.getProperty("creditcf.redis.port")), 10000);
		} else {
			pool = new JedisPool(config,
					RedisConfig.p.getProperty("creditcf.redis.ip"),
					Integer.valueOf(RedisConfig.p
							.getProperty("creditcf.redis.port")), 10000, AUTH);
		}

		// pool = new JedisPool(config, "121.40.205.162", 6379, 10000, AUTH);

	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (pool == null)
			createJedisPool();
	}

	/**
	 * 获取一个jedis 对象
	 * 
	 * @return
	 */
	public static Jedis getJedis() {

		if (pool == null)
			poolInit();
		return pool.getResource();
	}

	/**
	 * 归还一个连接
	 * 
	 * @param jedis
	 */
	public static void returnRes(Jedis jedis) {
		pool.returnResource(jedis);
	}

}

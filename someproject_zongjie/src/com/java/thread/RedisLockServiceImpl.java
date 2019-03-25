package com.java.thread;

import java.util.UUID;


//import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//import com.ipower365.lianyu.lock.service.RedisLockService;
//import com.ipower365.saas.basic.constants.BusinessCategoryEnum;


import com.java.connection.JedisPoolUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//@Service(RedisLockService.REDIS_LOCK)
public class RedisLockServiceImpl {
	
	public static void main(String[] args) {
		Jedis jedis=JedisPoolUtils.getJedis();
		//jedis.hset
	}
	
	//implements RedisLockService {
	/*//@Autowired
	private JedisPool jedisPool;

	private Logger log = LoggerFactory.getLogger(getClass());
	
	*//**
	 * 默认超时时间
	 *//*
    private static final Integer DEFAULT_EXPIRE_MSECS = 60 * 1000;
    
    *//**
     * 默认等待超时时间
     *//*
    private static final Integer DEFAULT_TIMEOUT_MSECS = 10 * 1000;
    
    *//**
     * 锁等待时间递减毫秒基数
     *//*
    private static final Integer DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;
    
    private static final String LOCK_STR = "lock_";
  
    private String get(final String key) {
    	if(StringUtils.isBlank(key)){
    		return null;
    	}
    	Jedis jedis = null;
    	try {
			jedis = getResource();
			String value = jedis.get(key);
			log.info("get redis success, key : {}, value : {}", key, value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("get redis error, key : {}", key);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
    	return null;
    }
    
    @SuppressWarnings("unused")
	private boolean setNX(final String key, final String value){
    	if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
    		return false;
    	}
    	Jedis jedis = null;
    	try {
			jedis = getResource();
			Long result = jedis.setnx(key, value);
			log.info("setNX redis success, key : {}, value : {}, result : {}", key, value, result);
			return result == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("setNX redis error, key : {}", key);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
    	return false;
    }
    
    *//**
     * * @param key
     * @param value
     * @param nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
     *          if it already exist.
     * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
     * @param time expire time in the units of <code>expx</code>
     * @return
     *//*
    private boolean set(final String key, final String value, final String nxxx, final String expx, final long time){
    	if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
    		return false;
    	}
    	Jedis jedis = null;
    	try {
			jedis = getResource();
			String result = jedis.set(key, value, nxxx, expx, time);
			log.info("set redis success, key : {}, value : {}, nxxx：{}, expx：{}, result : {}", key, value, nxxx, expx, result);
			return "OK".equals(result) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("setNX redis error, key : {}", key);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
    	return false;
    }
    
    @SuppressWarnings("unused")
	private String getSet(final String key, final String value){
    	if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
    		return null;
    	}
    	Jedis jedis = null;
    	try {
			jedis = getResource();
			String oldValue = jedis.getSet(key, value);
			log.info("getSet redis success, key : {}, value : {}, oldValue : {}", key, value, oldValue);
			return oldValue;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getset redis error, key : {}", key);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
    	return null;
    }
    
    private void del(final String key) {
    	if(StringUtils.isBlank(key)){
    		return;
    	}
    	Jedis jedis = null;
    	try {
			jedis = getResource();
			jedis.del(key);
			log.info("del redis success, key : {}", key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("del redis error, key : {}", key);
		} finally {
			if(jedis != null){
				jedis.close();
			}
		}
    }
  
    *//**
     *  获得锁
     * @param lockKey
     * @param expireMsecs
     * @return
     * @throws InterruptedException
     *//*
    public synchronized String lock(BusinessCategoryEnum businessCategoryEnum, final String lockKey, Integer expireMsecs, Integer timeOutMsecs) throws InterruptedException {
    	timeOutMsecs = timeOutMsecs == null ? DEFAULT_TIMEOUT_MSECS : timeOutMsecs;
    	expireMsecs = expireMsecs == null ? DEFAULT_EXPIRE_MSECS : expireMsecs;
    	String uuid = UUID.randomUUID().toString();
        String key = this.buildLockKey(businessCategoryEnum, lockKey);
    	while (timeOutMsecs >= 0) {
        	boolean flog = this.set(key, uuid, "nx", "px", expireMsecs);
            if (flog) {
            	// 获得锁成功
                return uuid;
            }
            
        	使用随机时间防止饥饿进程的出现,即当同时到达多个进程,
            只会有一个进程获得锁,其他的都用同样的频率进行尝试,后面有来了一些进行,也以同样的频率申请锁,这将可能导致前面来的锁得不到满足.
            使用随机的等待时间可以一定程度上保证公平性
             
            int sleepTimeMillis = RandomUtils.nextInt(50, DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
            timeOutMsecs -= sleepTimeMillis;
            Thread.sleep(sleepTimeMillis);
        }
        return null;
    }
    
    *//**
     * 锁续时
     *//*
    public synchronized boolean relock(BusinessCategoryEnum businessCategoryEnum, final String lockKey, final String uuid, Integer expireMsecs) {
    	if(StringUtils.isEmpty(uuid)){
    		return false;
    	}
    	expireMsecs = expireMsecs == null ? DEFAULT_EXPIRE_MSECS : expireMsecs;
    	String key = this.buildLockKey(businessCategoryEnum, lockKey);
        String currentUuid = this.get(key);
        if(uuid.equals(currentUuid)){
        	if(this.set(key, uuid, "xx", "px", expireMsecs)){
        		return true;
        	}
        }
        return false;
    }
  
    *//**
     * 释放锁
     *//*
    public synchronized void unlock(BusinessCategoryEnum businessCategoryEnum, final String lockKey, final String uuid) {
    	if(StringUtils.isEmpty(uuid)){
    		return;
    	}
    	String key = this.buildLockKey(businessCategoryEnum, lockKey);
    	String currentUuid = this.get(key); 
        if (uuid.equals(currentUuid)) {
        	this.del(key);
        }
    }
  
    *//**
     * 获得资源
     * @return
     *//*
    private Jedis getResource() {
        return jedisPool.getResource();
    }

	@Override
	public String lock(BusinessCategoryEnum businessCategoryEnum, String lockKey) throws InterruptedException {
		return this.lock(businessCategoryEnum, lockKey, DEFAULT_EXPIRE_MSECS);
	}

	@Override
	public String lock(BusinessCategoryEnum businessCategoryEnum, String lockKey, Integer expireMsecs) throws InterruptedException {
		return this.lock(businessCategoryEnum, lockKey, expireMsecs, DEFAULT_TIMEOUT_MSECS);
	}
	
	private String buildLockKey(BusinessCategoryEnum businessCategoryEnum, String lockKey){
		return LOCK_STR + businessCategoryEnum.getCode() + "_" + lockKey;
	}*/

}
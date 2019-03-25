package com.java.springboot.target;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * spring中的一些线程池
 * @author tzh
 *
 */
public class FarAway {
	
	public static void main(String[] args) {
		
		ThreadPoolTaskExecutor pool=new ThreadPoolTaskExecutor();
		
		//线程池中的缓冲队列
		pool.setQueueCapacity(200);  

		//维护的最小线程数
		pool.setCorePoolSize(5); 

		// 最大线程数
		pool.setMaxPoolSize(1000);
		
		//所允许的空闲时间
		pool.setKeepAliveSeconds(30000);  

		pool.initialize();
		
		pool.execute(new JumpPoolThread("test"), 1000);
		//pool.shutdown();
	}

}

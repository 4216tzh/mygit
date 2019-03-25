package com.java.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableCaching//开启redis缓存
/*@ServletComponentScan//开启过滤器
@EnableScheduling//开启定时任务
*/public class StartApplication {
	
	
	public static void main(String[] args) {
		/*ConfigurableApplicationContext context=SpringApplication.run(StartApplication.class, args);
		context.publishEvent(new Object());*/
		//context.close();
		SpringApplication.run(StartApplication.class, args);
	}
	
	
}

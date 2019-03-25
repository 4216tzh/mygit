package com.java.springboot.quartz.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class ScheduleConfig {
	
	@Autowired
	private MyJobFactory myJobFactory;
	
	@Bean(name="SchedulerFactory")
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean factoryBean=new SchedulerFactoryBean();
		// 延时启动
		factoryBean.setStartupDelay(20);
		factoryBean.setQuartzProperties(quartzProperties());
        // 自定义Job Factory，用于Spring注入
		factoryBean.setJobFactory(myJobFactory);
		return factoryBean;
	}
	
	@Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new FileSystemResource("conf/quartz.properties"));	    
 	    //在quartz.properties中的属性被读取并注入后再初始化对象
 	    propertiesFactoryBean.afterPropertiesSet();
 	    return propertiesFactoryBean.getObject();
    }
	
	/**
     * quartz初始化监听器
     * @return
     */
   @Bean
   public QuartzInitializerListener executorListener() {
      return new QuartzInitializerListener();
   }

   /**
    *  通过SchedulerFactoryBean获取Scheduler的实例
    */
   @Bean(name="Scheduler")
   public Scheduler scheduler() throws IOException {
   	Scheduler scheduler = schedulerFactoryBean().getScheduler();
       return scheduler;
   }

}

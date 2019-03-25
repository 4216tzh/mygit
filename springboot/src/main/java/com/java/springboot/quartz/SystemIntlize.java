package com.java.springboot.quartz;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@Transactional
public class SystemIntlize {
	
	private Logger logger = LoggerFactory.getLogger(SystemIntlize.class);
	
	@Autowired@Qualifier("Scheduler")
	private Scheduler scheduler;
	
	@PostConstruct
	public void init(){
		logger.info("任务开始!");
		String name=uniqueKey();
		String group=uniqueKey();
		try {
		TriggerKey triggerKey=TriggerKey.triggerKey(name, group);
		JobKey jobKey=JobKey.jobKey(name, group);
		if(scheduler.checkExists(jobKey)) {
			
		}else {
			JobDetail jobDetail=JobBuilder.newJob(FriSchedule.class).usingJobData("bye bye", "shanghai").
									withIdentity(jobKey).withIdentity(name, group).
									withDescription("这是一个测试").build();
			CronScheduleBuilder cronScheduleBuilder= CronScheduleBuilder.cronSchedule("0/5 * * ? * *").withMisfireHandlingInstructionDoNothing();
			CronTrigger cronTrigger=TriggerBuilder.newTrigger()
										   .withIdentity(triggerKey).withSchedule(cronScheduleBuilder)
										   .withDescription("创建一个任务并按时执行")
										   .build();
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static String uniqueKey() {
		String key=UUID.randomUUID().toString();
		return key.replaceAll("-", "");
	}

}

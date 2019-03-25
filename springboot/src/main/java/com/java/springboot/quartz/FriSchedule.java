package com.java.springboot.quartz;

import java.util.Calendar;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution//禁止并发执行同一个Job 
public class FriSchedule implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail=context.getJobDetail();
		System.out.println("描述:"+jobDetail.getDescription());
		Calendar c=Calendar.getInstance();
		System.out.println("这是一个测试："+c.getTime().toString());
		
	}

}

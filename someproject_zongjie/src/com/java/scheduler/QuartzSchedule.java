package com.java.scheduler;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 创建任务调度，并执行
 * @author Administrator
 * 
 */
public class QuartzSchedule {
	
	/**
	 * 创建调度器
	 * @return
	 * @throws SchedulerException
	 */
	public Scheduler getScheduler() throws SchedulerException{
		SchedulerFactory factory=new StdSchedulerFactory();
		return factory.getScheduler();
	}
	
	/**
	 * 执行任务
	 * @throws SchedulerException
	 */
	 public void schedulerJob() throws SchedulerException{
	        //创建任务
	        //JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
	        //这个带有参数至job中(usingJobData)
	        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1")
	        								.usingJobData("name", "这是一个测试aaaa").build();
	        
	        //
	       /* JobDetail job=new JobDetailImpl("job1", "group1", MyJob.class);//过时方法
	        job.getJobDataMap().put("name", "这是一个测试");*/
	        
	        
	        
	        //创建触发器 每3秒钟执行一次
	        /*Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
	                            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
	                            .build();
	        Trigger triggers = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                    .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInHours(1))
                    .build();*/
	        //设定时间每五秒执行一次
	        CronTrigger  trigger1=TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * *"))
                    .build();
	        trigger1.getJobDataMap().put("name", "这是一个测试");
	        Scheduler scheduler = getScheduler();
	        
	        //将任务及其触发器放入调度器
	        scheduler.scheduleJob(jobDetail, trigger1);
	        //调度器开始调度任务
	        scheduler.start();
	        
	        //调度器结束调度任务
	        //scheduler.shutdown(true);
	        
	    }
	    
		public static void main(String[] args) throws SchedulerException {
	    	QuartzSchedule mainScheduler = new QuartzSchedule();
	        mainScheduler.schedulerJob();
	        
	    } 
	

}

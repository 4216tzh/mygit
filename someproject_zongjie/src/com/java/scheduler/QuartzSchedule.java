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
 * ����������ȣ���ִ��
 * @author Administrator
 * 
 */
public class QuartzSchedule {
	
	/**
	 * ����������
	 * @return
	 * @throws SchedulerException
	 */
	public Scheduler getScheduler() throws SchedulerException{
		SchedulerFactory factory=new StdSchedulerFactory();
		return factory.getScheduler();
	}
	
	/**
	 * ִ������
	 * @throws SchedulerException
	 */
	 public void schedulerJob() throws SchedulerException{
	        //��������
	        //JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
	        //������в�����job��(usingJobData)
	        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1")
	        								.usingJobData("name", "����һ������aaaa").build();
	        
	        //
	       /* JobDetail job=new JobDetailImpl("job1", "group1", MyJob.class);//��ʱ����
	        job.getJobDataMap().put("name", "����һ������");*/
	        
	        
	        
	        //���������� ÿ3����ִ��һ��
	        /*Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
	                            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
	                            .build();
	        Trigger triggers = TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                    .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInHours(1))
                    .build();*/
	        //�趨ʱ��ÿ����ִ��һ��
	        CronTrigger  trigger1=TriggerBuilder.newTrigger().withIdentity("trigger1", "group3")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * *"))
                    .build();
	        trigger1.getJobDataMap().put("name", "����һ������");
	        Scheduler scheduler = getScheduler();
	        
	        //�������䴥�������������
	        scheduler.scheduleJob(jobDetail, trigger1);
	        //��������ʼ��������
	        scheduler.start();
	        
	        //������������������
	        //scheduler.shutdown(true);
	        
	    }
	    
		public static void main(String[] args) throws SchedulerException {
	    	QuartzSchedule mainScheduler = new QuartzSchedule();
	        mainScheduler.schedulerJob();
	        
	    } 
	

}

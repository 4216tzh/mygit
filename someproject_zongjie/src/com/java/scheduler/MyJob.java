package com.java.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.java.file.FileUtil;

public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail job=context.getJobDetail();
		System.out.println(job.getJobDataMap().getString("name"));
		byte[] b=FileUtil.readFileByByte("C:/Users/Administrator/Desktop/����˵���ĵ�.txt");
		String str=new String(b);
		System.out.println("�ļ�����:"+str);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		System.out.println("ʱ����:"+sdf.format(new Date()));
	}

}

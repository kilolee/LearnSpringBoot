package com.kilo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 创建Cron定时任务2
 * Created by kilo on 2018/5/15.
 */
public class ScheduledJob2 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("schedule job2 is running ...");
    }
}

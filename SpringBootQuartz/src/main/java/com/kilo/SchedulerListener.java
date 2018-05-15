package com.kilo;

import com.kilo.scheduler.CronSchedulerJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 在特定时间启动定时任务
 * 需要考虑重复启动定时任务的情况，重复启动定时任务会报错。
 * Created by kilo on 2018/5/15.
 */
@EnableScheduling
@Component
public class SchedulerListener {

    @Autowired
    public CronSchedulerJob schedulerJob;

    @Scheduled(cron = "0 37 16 15 5 ?")
    public void schedule() throws SchedulerException {
        System.out.println("..........特定时间...........");
        schedulerJob.scheduleJobs();
    }

}

package com.kilo.scheduler;

import com.kilo.job.ScheduledJob1;
import com.kilo.job.ScheduledJob2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 构建 jobDetail、CronTrigger，最后使用 scheduler 关联 jobDetail 和 CronTrigger
 * Created by kilo on 2018/5/15.
 */
@Component
public class CronSchedulerJob {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleJob1(scheduler);
        scheduleJob2(scheduler);
    }


    public void scheduleJob1(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob1.class).withIdentity("job1", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/6 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }


    public void scheduleJob2(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob2.class).withIdentity("job2", "group2").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/12 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group2").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }


}

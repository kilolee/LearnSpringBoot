package com.kilo;

import com.kilo.scheduler.CronSchedulerJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 推荐使用
 * 启动时调用 scheduleJobs() 来启动定时任务
 * Created by kilo on 2018/5/15.
 */
@Component
public class MyStartupRunner implements CommandLineRunner {
    @Autowired
    public CronSchedulerJob schedulerJob;

    @Override
    public void run(String... args) throws Exception {
        schedulerJob.scheduleJobs();
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}

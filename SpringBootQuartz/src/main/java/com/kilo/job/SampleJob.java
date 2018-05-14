package com.kilo.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by kilo on 2018/5/14.
 */
public class SampleJob extends QuartzJobBean {

    private String name;

    public void setName(String name) {
        this.name = name;
    }


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println(String.format("Hello %s!", this.name));
    }
}

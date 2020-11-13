package com.springbootdemo.demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class TestJob {
    public static void main(String[] args) {
        try {
            // 创建调度器
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            // 创建任务
            JobDetail jobDetail = JobBuilder.newJob(DataQualityJob.class).withIdentity("job1", "group1").build();
            // 创建出发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trgger1", "group1").withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(2).repeatForever()).build();
            // 执行
            scheduler.scheduleJob(jobDetail, trigger);
            System.out.println("--------scheduler start ! ------------");
            scheduler.start();
            //睡眠
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scheduler.shutdown();
            System.out.println("--------scheduler shutdown ! ------------");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

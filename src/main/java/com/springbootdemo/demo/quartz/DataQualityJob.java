package com.springbootdemo.demo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * 数据质量定时任务job
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution // 禁止同一job类实例（jobDetail）并发执行
@Slf4j
public class DataQualityJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("-------------------------------aa------------------------------");
    }
}

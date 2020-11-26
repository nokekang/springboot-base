package com.springbootdemo.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author sjk
 * @date 2020/11/23 14:01
 */
@Component
@Slf4j
public class Task1 {
//    @Scheduled(cron = "* * * * * ?")
    public void task1(){
        log.info("task1-------------------------");
    }

//    @Scheduled(fixedRate = 10*1000)
    public void task2(){
        log.info("task2开始-------------------------");
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("task结束-------------------------");
    }
}

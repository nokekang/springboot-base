package com.springbootdemo.demo.base.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

/**
 * @author sjk
 * @date 2020/12/1 14:08
 */
public class Test1 {

    private static final Logger logger = LoggerFactory.getLogger(Test1.class);
    
    public static void main(String[] args) {
//        testThreadPoolExecutor();
        try {
//            int i =1/0;
            String a =null;
            a.split(",");
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("test{},{}","zahngs",e);
        }

}


    public static void testThreadPoolExecutor() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 15, 15,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        // 抛弃
        for (int i = 1; i <= 20; i++) {
            final String name= "第"+i+"个线程";
            logger.info(name + "开始创建");
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                   logger.info(name + "--开始执行");
                    try {
                        Thread.sleep(10000);
                       logger.info(name + "---1s");
                        Thread.sleep(1000);
                       logger.info(name + "---2s");
                        Thread.sleep(1000);
                       logger.info(name + "---3s");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}

package com.springbootdemo.demo.base.Thread;

/**
 * @program: noframebase
 * @description: 守护线程demo
 * @author: sjk
 * @create: 2020-01-15 11:18
 */
public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("守护线程心跳。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();


        Thread userThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("用户线程心跳。。。");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        userThread.start();
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
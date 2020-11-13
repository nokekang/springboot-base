package com.springbootdemo.demo.base;

import com.springbootdemo.demo.controller.CityRestController;
import com.springbootdemo.demo.domain.City;

/**
 * @program: springboot-base
 * @description: 多重for循环效率优化
 * @author: sjk
 * @create: 2020-02-18 15:12
 */
public class Test1 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int a = 100;
        int b = 100;
        int c = 100;
        for (int i = 0; i < a; i++) {
            for (int i1 = 0; i1 < b; i1++) {
                for (int i2 = 0; i2 < c; i2++) {
                    City city = new City();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        long end1  = System.currentTimeMillis();

        System.out.println("总毫秒数：" + (end1 - start));

        for (int i3 = 0; i3 < 1000000; i3++) {
            City city = new City();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println("总毫秒数：" + (end2 - end1));


    }

}
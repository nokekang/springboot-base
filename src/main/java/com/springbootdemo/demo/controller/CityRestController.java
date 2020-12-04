package com.springbootdemo.demo.controller;

import com.springbootdemo.demo.service.CityService;
import com.springbootdemo.demo.service.TransactionTestService;
import lombok.extern.slf4j.Slf4j;
import com.springbootdemo.demo.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
@RequestMapping("city")
@Slf4j
public class CityRestController {
	
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "list")
    public List<City> findList(City city) {
        List<City> citys= cityService.findAll();
        return citys;
    }

    @RequestMapping(value = "test1")
    public String test1(City city) {
        List<City> citys = new ArrayList<>();
        int size = 10000000;
        for (int i = 0; i < size; i++) {
            City city1 = new City();
            city1.setCityName("张三");
            citys.add(city1);
        }
        log.info("list创建完成-----------" + citys.size());
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
    	log.info("通过城市名查询");
        City cityByName = cityService.findCityByName(cityName);
        log.info("城市信息:{}",cityByName);
        return cityByName;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody City city) {
        log.info("参数City:{}", city);
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            // 线程安全问题 TODO
            cityService.save(city);
        } catch (Exception e) {
            e.printStackTrace();
            reentrantLock.unlock();
        }
        return "ok";
    }

    @RequestMapping(value = "/save1", method = RequestMethod.POST)
    public String save(@RequestBody Object object) {
        System.out.println(object);
        return "ok";
    }

    @Autowired
    private TransactionTestService transactionTestService;

    @RequestMapping(value = "/testTra", method = RequestMethod.POST)
    public String testTra(@RequestBody Object object) {
        transactionTestService.testTransaction();
        return "ok";
    }
}

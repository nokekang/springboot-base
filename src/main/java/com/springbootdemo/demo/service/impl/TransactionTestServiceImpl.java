package com.springbootdemo.demo.service.impl;

import com.springbootdemo.demo.dao.CityDao;
import com.springbootdemo.demo.domain.City;
import com.springbootdemo.demo.service.CityService;
import com.springbootdemo.demo.service.TransactionTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sjk
 * @date 2020/12/4 14:50
 */
@Service
public class TransactionTestServiceImpl implements TransactionTestService {

    @Autowired
    private CityService  cityService;

    @Autowired
    private CityDao cityDao;


    private static final Logger logger = LoggerFactory.getLogger(TransactionTestServiceImpl.class);

    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testTransaction() {

        City cityB = new City();
        cityB.setCityName("北京");
        cityB.setDescription("北京");
        cityB.setProvinceId("北京");
        cityService.save(cityB);

        City cityC = new City();
        cityC.setCityName("1");
        cityC.setDescription("1");
        cityC.setProvinceId("1");
        cityService.exceptionSave(cityC);

        City cityA = new City();
        cityA.setCityName("20");
        cityA.setDescription("20");
        cityA.setProvinceId("20");
        cityDao.save(cityA);
    }
}

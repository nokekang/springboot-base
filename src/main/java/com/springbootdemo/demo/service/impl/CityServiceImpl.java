package com.springbootdemo.demo.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.springbootdemo.demo.dao.CityDao;
import com.springbootdemo.demo.service.CityService;
import com.springbootdemo.demo.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleUnresolved;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    public City findCityByName(String cityName) {

		Thread daemonThread = new Thread(new Runnable() {
			@Override
			public void run() {
//				while (true) {
					try {
						Thread.sleep(1000);
						System.out.println("守护线程心跳。。。");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//				}
			}
		});
		daemonThread.setDaemon(true);
		daemonThread.start();

		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程结束");
//		City byName = cityDao.findByName(cityName);
        return new City();
    }

	@Override
	public List<City> findAll() {
		PageHelper.startPage(2,5);
		return cityDao.findAll();
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(City city) {
		cityDao.save(city);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void exceptionSave(City city) {
		cityDao.save(city);
	}


}

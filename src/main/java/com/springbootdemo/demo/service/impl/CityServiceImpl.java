package com.springbootdemo.demo.service.impl;

import java.util.List;

import com.springbootdemo.demo.dao.CityDao;
import com.springbootdemo.demo.service.CityService;
import com.springbootdemo.demo.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return cityDao.findByName(cityName);
    }

	@Override
	public List<City> findAll() {
		return cityDao.findAll();
	}

	@Override
	@Transactional
	public void save(City city) {
		cityDao.save(city);
	}

}

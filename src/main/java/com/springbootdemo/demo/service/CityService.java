package com.springbootdemo.demo.service;

import java.util.List;

import com.springbootdemo.demo.domain.City;

/**
 * 城市业务逻辑接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface CityService {


    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);

	/**
	 * 查询所有
	 */
    List<City> findAll();

	/**保存入库
	 * @param city
	 */
	void save(City city);

	void exceptionSave(City city);
}

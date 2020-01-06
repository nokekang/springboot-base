package com.springbootdemo.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.springbootdemo.demo.domain.City;
import org.springframework.stereotype.Repository;

/**
 * 城市 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
 // 可以不写，但是不写idea编译报错
public interface CityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);

	/**
	 * 查找所有
	 * @return
	 */
	List<City> findAll();

	/**保存
	 * @param city
	 */
	void save(City city);
}

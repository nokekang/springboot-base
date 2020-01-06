package com.springbootdemo.demo.controller;

import com.springbootdemo.demo.service.CityService;
import lombok.extern.slf4j.Slf4j;
import com.springbootdemo.demo.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
@RequestMapping("/city")
@Slf4j
public class CityRestController {
	
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
    	log.info("通过城市名查询");
        City cityByName = cityService.findCityByName(cityName);
        log.info("城市信息:{}",cityByName);
        return cityByName;
    }

}

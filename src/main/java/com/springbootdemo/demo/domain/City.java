package com.springbootdemo.demo.domain;

import lombok.Data;
import lombok.ToString;

/**
 * 城市实体类
 *
 * Created by bysocket on 07/02/2017.
 */
@Data
@ToString
public class City{

    /**
     * 城市编号
     */
    private Integer id;

    /**
     * 省份编号
     */
    private String provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;

}

package com.example.demo.dao;

import com.example.demo.entity.Area;

import java.util.List;

/**
 * 类名称: AreaDao
 * 类描述: 数据库调用接口，对service层封装数据库操作，mybatis通过在/resources/mapper下定义对应的SQL语句来实现
 *
 * @author: wqy
 * 创建时间: 2020/5/23 8:55 下午
 * Version 1.0
 */
public interface AreaDao {
    List<Area> queryArea();
    Area queryAreaById(int areaId);
    int insertArea(Area area);
    int updateArea(Area area);
    int deleteArea(int areaId);
}

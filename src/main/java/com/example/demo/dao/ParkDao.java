package com.example.demo.dao;

import com.example.demo.entity.Park;

import java.util.List;

/**
 * 类名称: ParkDao
 * 类描述: Park数据库调用接口
 *
 * @author: wqy
 * 创建时间: 2020/6/18 6:15 下午
 * Version 1.0
 */

public interface ParkDao {
    int addUserToPark(String userId, int parkId);
    List<Park> getUserPark(String userId);
    List<Park> listAllParks();
    int removeUserFromPark(String userId, int parkId);
}

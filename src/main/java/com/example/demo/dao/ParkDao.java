package com.example.demo.dao;

import com.example.demo.entity.Park;
import com.example.demo.entity.Spot;

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
    int addSpotToPark(int parkId, int spotId);
    List<Spot> listSpotsInPark(int parkId);
    int addPark(Park park);
    int deletePark(int parkId);
    int removeSpotFromPark(int parkId, int spotId);
}

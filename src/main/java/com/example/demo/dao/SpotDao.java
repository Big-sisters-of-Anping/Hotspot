package com.example.demo.dao;

import com.example.demo.entity.Spot;

import java.util.List;

/**
 * 类名称: SpotDao
 * 类描述: Spot数据库调用接口
 *
 * @author: wqy
 * 创建时间: 2020/6/18 6:03 下午
 * Version 1.0
 */
public interface SpotDao {
    List<Spot> listAreaSpots(double minLongitude, double maxLongitude, double minLatitude, double maxLatitude);
    List<Spot> listAllSpots();
    List<Spot> searchSpotByName(String spotName);
    Spot querySpotById(int spotId);
    int insertSpot(Spot spot);
    int updateSpot(Spot spot);
    int insertUserLocation(int userId, int spotId);
    int updateUserLocation(int userId, int spotId);
    int existUserLocation(int userId);
}

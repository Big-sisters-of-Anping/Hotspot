package com.example.demo.service;

import com.example.demo.entity.Spot;

import java.util.List;

/**
 * 类名称: SpotService
 * 类描述: Spot服务接口类
 *
 * @author: wqy
 * 创建时间: 2020/6/18 8:52 下午
 * Version 1.0
 */
public interface SpotService {
    List<Spot> listAreaSpots(double minLongitude, double maxLongitude, double minLatitude, double maxLatitude);
    List<Spot> listAllSpots();
    Spot querySpotById(int spotId);
    boolean insertSpot(Spot area);
}

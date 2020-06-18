package com.example.demo.service;

import com.example.demo.entity.Park;

import java.util.List;

/**
 * 类名称: ParkService
 * 类描述: Park服务接口
 *
 * @author: wqy
 * 创建时间: 2020/6/18 6:28 下午
 * Version 1.0
 */
public interface ParkService {
    boolean addUserToPark(String userId, int parkId);
    List<Park> getUserPark(String userId);
    List<Park> listAllParks();
    boolean removeUserFromPark(String userId, int parkId);
}

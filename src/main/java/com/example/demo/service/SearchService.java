package com.example.demo.service;

import com.example.demo.entity.Spot;

import java.util.List;

/**
 * 类名称: SearchService
 * 类描述: 搜索服务接口
 *
 * @author: wqy
 * 创建时间: 2020/6/26 10:29 上午
 * Version 1.0
 */

public interface SearchService {
    List<Spot> searchSpotsByName(String spotName);
    List<Spot> searchSpotsByNameWithLocation(String spotName, double longitude, double latitude);
}

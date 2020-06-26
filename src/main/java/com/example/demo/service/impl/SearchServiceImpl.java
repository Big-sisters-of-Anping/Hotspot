package com.example.demo.service.impl;

import com.example.demo.dao.SpotDao;
import com.example.demo.entity.Spot;
import com.example.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名称: SearchServiceImpl
 * 类描述: 搜索服务实现
 *
 * @author: wqy
 * 创建时间: 2020/6/26 10:29 上午
 * Version 1.0
 */

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SpotDao spotDao;

    @Override
    public List<Spot> searchSpotsByName(String spotName) {
        if (!"".equals(spotName))
            return spotDao.searchSpotByName(spotName);
        else
            throw new RuntimeException("spotName不能为空！");
    }
}
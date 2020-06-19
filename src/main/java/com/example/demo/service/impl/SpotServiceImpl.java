package com.example.demo.service.impl;

import com.example.demo.dao.SpotDao;
import com.example.demo.entity.Spot;
import com.example.demo.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类名称: SpotServiceImpl
 * 类描述: Spot服务实现类
 *
 * @author: wqy
 * 创建时间: 2020/6/18 8:52 下午
 * Version 1.0
 */

@Service
public class SpotServiceImpl implements SpotService {
    @Autowired
    private SpotDao spotDao;

    @Override
    public List<Spot> listAreaSpots(double minLongitude, double maxLongitude, double minLatitude, double maxLatitude) {
        return spotDao.listAreaSpots(minLongitude, maxLongitude, minLatitude, maxLatitude);
    }

    @Override
    public List<Spot> listAllSpots() {
        return spotDao.listAllSpots();
    }

    @Override
    public Spot querySpotById(int spotId) {
        return spotDao.querySpotById(spotId);
    }

    @Transactional
    @Override
    public int insertSpot(Spot spot) {
        if (spot.getSpotName() != null && !"".equals(spot.getSpotName())){
            try {
                int effectedNum = spotDao.insertSpot(spot);
                if (effectedNum > 0)
                    return spot.getSpotId();
                else {
                    throw new RuntimeException("添加地点失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("添加地点失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("地点名不能为空！");
        }
    }
}
package com.example.demo.service.impl;

import com.example.demo.dao.SpotDao;
import com.example.demo.entity.Spot;
import com.example.demo.service.SpotService;
import io.swagger.models.auth.In;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    /**
     * 获取某点某个范围内的Spot列表
     * @param longitude
     * @param latitude
     * @param rangLongitude
     * @param rangLatitude
     * @return
     */
    @Override
    public List<Spot> listRangeSpots(double longitude, double latitude, double rangLongitude, double rangLatitude){
        double minLongitude, maxLongitude, minLatitude, maxLatitude;
        maxLongitude = (longitude + rangLongitude > 180)? 180: longitude + rangLongitude;
        minLongitude = (longitude - rangLongitude < -180)? -180 : longitude - rangLongitude;
        maxLatitude = (latitude + rangLatitude > 90) ? 90 : latitude + rangLatitude;
        minLatitude = (latitude - rangLatitude < -90) ? -90 : latitude - rangLatitude;
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

    @Transactional
    @Override
    public boolean updateSpot(Spot spot) {
        try {
            int effectedNum = spotDao.updateSpot(spot);
            if (effectedNum > 0)
                return true;
            else {
                throw new RuntimeException("更新地点信息失败！");
            }
        }catch (Exception e){
            throw new RuntimeException("更新地点信息失败：" + e.getMessage());
        }
    }

    /**
     * 更新用户位置
     * @param userId
     * @param longitude
     * @param latitude
     * @return
     */
    @Transactional
    @Override
    public boolean updateUserLocation(int userId, double longitude, double latitude) {
        double range_longitude = 1;     // 经度1度 ~ 111.11km
        double range_latitude = 1;      // 纬度1度 ~ 111km
        try {
            List<Spot> range_spots = listRangeSpots(longitude, latitude, range_longitude, range_latitude);
            if (range_spots.size() > 0) {
                /*
                 * 获取距离最近的spotId
                 */
                HashMap<Integer, Double> distances = new HashMap<Integer, Double>();
                for (int i = 0; i < range_spots.size(); ++i) {
                    GlobalCoordinates from = new GlobalCoordinates(longitude, latitude);
                    GlobalCoordinates to = new GlobalCoordinates(range_spots.get(i).getLongitude(), range_spots.get(i).getLatitude());
                    GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, from, to);
                    distances.put(i, geoCurve.getEllipsoidalDistance());
                }
                List<HashMap.Entry<Integer, Double>> list = new ArrayList<HashMap.Entry<Integer, Double>>(distances.entrySet()); //转换为list
                list.sort(new Comparator<HashMap.Entry<Integer, Double>>() {
                    @Override
                    public int compare(HashMap.Entry<Integer, Double> o1, HashMap.Entry<Integer, Double> o2) {
                        return (int)(o1.getValue() - o2.getValue());
                    }
                });
                int spotId = range_spots.get(list.get(0).getKey()).getSpotId();

                /*
                 * 更新用户位置
                 */
                try {
                    int spotNum = spotDao.existUserLocation(userId);
                    int effectedNum = 0;
                    if (spotNum > 0){
                        effectedNum = spotDao.updateUserLocation(userId, spotId);
                    }else {
                        effectedNum = spotDao.insertUserLocation(userId, spotId);
                    }
                    if (effectedNum > 0)
                        return true;
                    else
                        throw new RuntimeException("已计算出最近的注册地点你，更新用户位置失败：");
                }catch (Exception e){
                    throw new RuntimeException("已计算出最近的注册地点你，更新用户位置失败：" + e.getMessage());
                }
            }
            else {
                throw new RuntimeException("用户附近没有注册的地点！");
            }
        }catch (Exception e){
            throw new RuntimeException("更新用户位置失败：" + e.getMessage());
        }
    }
}
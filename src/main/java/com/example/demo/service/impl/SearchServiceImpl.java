package com.example.demo.service.impl;

import com.example.demo.dao.SpotDao;
import com.example.demo.entity.Spot;
import com.example.demo.service.SearchService;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    // Calculate the Least common multiple
    public static int LeastCommonMultiple(int a, int b){
        if(a < b){
            int t = a;
            a = b;
            b = t;
        }
        while(b != 0){
            if(a == b){
                return a;
            }else{
                int k = a % b;
                a = b;
                b = k;
            }
        }
        return a;
    }

    @Override
    public List<Spot> searchSpotsByName(String spotName) {
        if (!"".equals(spotName)) {
            List<Spot> spots = spotDao.searchSpotByName(spotName);
            if (spots.size() > 1){
                spots.sort(new Comparator<Spot>() {
                    @Override
                    public int compare(Spot o1, Spot o2) {
                        int least_common = LeastCommonMultiple(o1.getSuggestedPeople(), o2.getSuggestedPeople());
                        return (o1.getRealtimePeople()*(least_common/o1.getSuggestedPeople()) - o2.getRealtimePeople()*(least_common/o2.getSuggestedPeople()));
                    }
                });
            }
            return spots;
        }
        else
            throw new RuntimeException("spotName不能为空！");
    }

    @Override
    public List<Spot> searchSpotsByNameWithLocation(String spotName, double longitude, double latitude) {
        if (!"".equals(spotName)) {
            List<Spot> spots = spotDao.searchSpotByName(spotName);
            if (spots.size() > 1){
                for (int i = 0; i < spots.size(); ++i) {
                    GlobalCoordinates from = new GlobalCoordinates(longitude, latitude);
                    GlobalCoordinates to = new GlobalCoordinates(spots.get(i).getLongitude(), spots.get(i).getLatitude());
                    GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, from, to);
                    spots.get(i).setDistance(geoCurve.getEllipsoidalDistance());
                }
                spots.sort(new Comparator<Spot>() {
                    @Override
                    public int compare(Spot o1, Spot o2) {
                        return (int)(o1.getDistance() - o2.getDistance());
                    }
                });
            }
            return spots;
        }
        else
            throw new RuntimeException("spotName不能为空！");
    }
}
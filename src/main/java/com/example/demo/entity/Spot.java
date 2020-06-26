package com.example.demo.entity;

import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * 类名称: Spot
 * 类描述: Spot实体类，地图上具体的某个地区，人数统计的最小单位
 *
 * @author: wqy
 * 创建时间: 2020/6/18 5:53 下午
 * Version 1.0
 */

public class Spot {
    private Integer spotId;
    private String spotName;
    private Integer realtimePeople;
    private Integer suggestedPeople;
    private GlobalCoordinates location = new GlobalCoordinates(0, 0);

    public double getLongitude() {
        return location.getLongitude();
    }

    public void setLongitude(double longitude) {
        location.setLongitude(longitude);
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public void setLatitude(double latitude) {
        location.setLatitude(latitude);
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public Integer getRealtimePeople() {
        return realtimePeople;
    }

    public void setRealtimePeople(Integer realtimePeople) {
        this.realtimePeople = realtimePeople;
    }

    public Integer getSuggestedPeople() {
        return suggestedPeople;
    }

    public void setSuggestedPeople(Integer suggestedPeople) {
        this.suggestedPeople = suggestedPeople;
    }
}
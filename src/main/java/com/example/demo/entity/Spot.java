package com.example.demo.entity;

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
    private Double longitude;
    private Double latitude;
    private Integer realtimePeople;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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
}
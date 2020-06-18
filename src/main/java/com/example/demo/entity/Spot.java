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
    private Integer spot_id;
    private String spot_name;
    private Double longitude;
    private Double latitude;

    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

    public String getSpot_name() {
        return spot_name;
    }

    public void setSpot_name(String spot_name) {
        this.spot_name = spot_name;
    }

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
}
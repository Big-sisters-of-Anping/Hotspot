package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.util.List;

/**
 * 类名称: Spot
 * 类描述: Spot实体类，地图上具体的某个地区，人数统计的最小单位
 *
 * @author: wqy
 * 创建时间: 2020/6/18 5:53 下午
 * Version 1.0
 */

public class Spot{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer spotId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String spotName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer realtimePeople;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer suggestedPeople;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer spotType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double distance;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GlobalCoordinates location = new GlobalCoordinates(0, 0);

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SpotOrderTime> spotOrderTimeList;

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

    public Double getDistance(){
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    public Integer getSpotType() {
        return spotType;
    }

    public void setSpotType(Integer spotType) {
        this.spotType = spotType;
    }

    public List<SpotOrderTime> getSpotOrderTimeList() {
        return spotOrderTimeList;
    }

    public void setSpotOrderTimeList(List<SpotOrderTime> spotOrderTimeList) {
        this.spotOrderTimeList = spotOrderTimeList;
    }
}
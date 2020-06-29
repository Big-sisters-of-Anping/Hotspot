package com.example.demo.entity;

import java.sql.Date;

/**
 * 类名称: SpotOrderTime
 * 类描述: 某地可预约时间表 实体类
 *
 * @author: wqy
 * 创建时间: 2020/6/20 11:41 下午
 * Version 1.0
 */

public class SpotOrderTime {
    static public String formatTimeHHMM(String time){
        String[] parts = time.split(":");
        return parts[0] + ":" + parts[1];
    }

    private Integer spotOrderTimeId;
    private Integer spotId;
    private String spotName;
    private String startTime;
    private String endTime;
    private Integer suggestedPeople;
    private Integer orderedPeople;

    public Integer getSpotOrderTimeId() {
        return spotOrderTimeId;
    }

    public void setSpotOrderTimeId(Integer spotOrderTimeId) {
        this.spotOrderTimeId = spotOrderTimeId;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getSuggestedPeople() {
        return suggestedPeople;
    }

    public void setSuggestedPeople(Integer suggestedPeople) {
        this.suggestedPeople = suggestedPeople;
    }

    public Integer getOrderedPeople() {
        return orderedPeople;
    }

    public void setOrderedPeople(Integer orderedPeople) {
        this.orderedPeople = orderedPeople;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public void formatTime(){
        this.startTime = formatTimeHHMM(startTime);
        this.endTime = formatTimeHHMM(endTime);
    }
}
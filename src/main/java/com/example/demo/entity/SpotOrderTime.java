package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 类名称: SpotOrderTime
 * 类描述: 某地可预约时间表 实体类
 *
 * @author: wqy
 * 创建时间: 2020/6/20 11:41 下午
 * Version 1.0
 */

public class SpotOrderTime {
    public static String formatTimeHHMM(String time){
        if (time == null || "".equals(time)) return null;
        String[] parts = time.split(":");
        return parts[0] + ":" + parts[1];
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer spotOrderTimeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer spotId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String spotName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String startTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String endTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer suggestedPeople;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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
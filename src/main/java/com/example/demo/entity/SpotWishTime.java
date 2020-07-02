package com.example.demo.entity;

/**
 * 类名称: SpotWishTime
 * 类描述: // TODO
 *
 * @author: wqy
 * 创建时间: 2020/7/2 9:47 下午
 * Version 1.0
 */

public class SpotWishTime {
    static public String formatTimeHHMM(String time){
        String[] parts = time.split(":");
        return parts[0] + ":" + parts[1];
    }

    private Integer spotWishTimeId;
    private Integer spotId;
    private String spotName;
    private String startTime;
    private String endTime;

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

    public Integer getSpotWishTimeId() {
        return spotWishTimeId;
    }

    public void setSpotWishTimeId(Integer spotWishTimeId) {
        this.spotWishTimeId = spotWishTimeId;
    }
}
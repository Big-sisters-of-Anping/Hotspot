package com.example.demo.entity;

import java.sql.Date;

/**
 * 类名称: Wish
 * 类描述: 想去 实体类
 *
 * @author: wqy
 * 创建时间: 2020/7/2 9:37 下午
 * Version 1.0
 */

public class Wish {
    private int wishId;
    private int userId;
    private Date wishDate;
    private SpotWishTime wishTime = new SpotWishTime();

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getWishDate() {
        return wishDate;
    }

    public void setWishDate(Date wishDate) {
        this.wishDate = wishDate;
    }

    public String getStartTime() {
        return wishTime.getStartTime();
    }

    public String getSpotName() {
        return wishTime.getSpotName();
    }

    public void setSpotName(String spotName) {
        wishTime.setSpotName(spotName);
    }

    public void setStartTime(String startTime) {
        wishTime.setStartTime(startTime);
    }

    public String getEndTime() {
        return wishTime.getEndTime();
    }

    public void setEndTime(String endTime) {
        wishTime.setEndTime(endTime);
    }

    public int getSpotWishTimeId() { return wishTime.getSpotWishTimeId();}

    public void setSpotWishTimeId(int spotWishTimeId) { wishTime.setSpotWishTimeId(spotWishTimeId);}

    public void formatTime(){
        this.wishTime.formatTime();
    }
}
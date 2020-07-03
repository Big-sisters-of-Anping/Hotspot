package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int wishId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int userId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date wishDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    public SpotWishTime getWishTime() {
        return wishTime;
    }

    public void setWishTime(SpotWishTime wishTime) {
        this.wishTime = wishTime;
    }

    public void formatTime(){
        this.wishTime.formatTime();
    }
}
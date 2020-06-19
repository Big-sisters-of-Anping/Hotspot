package com.example.demo.entity;

import java.util.Date;

/**
 * 类名称: Order
 * 类描述: 预约实体类
 *
 * @author: wqy
 * 创建时间: 2020/6/19 2:25 下午
 * Version 1.0
 */
public class Order {
    private int orderId;
    private int userId;
    private int spotId;
    private Date startTime;
    private Date endTime;
    private int orderStatus;
    private String note;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String statusToString(){
        switch (orderStatus){
            case 0:
                return "未处理";
            case 1:
                return "成功";
            case 2:
                return "失败";
            default:
                return "未定义";
        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderStatus() {
        return orderStatus;
    }
}
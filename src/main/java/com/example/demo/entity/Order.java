package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * 类名称: Order
 * 类描述: 预约实体类
 *
 * @author: wqy
 * 创建时间: 2020/6/19 2:25 下午
 * Version 1.0
 */
public class Order {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int orderId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int userId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date orderDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SpotOrderTime orderTime = new SpotOrderTime();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int orderStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    public String statusToString(){
        switch (orderStatus){
            case 0:
                return "未处理";
            case 1:
                return "成功";
            case 2:
                return "失败";
            case 3:
                return "已取消";
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

//    public int getSpotId() {
//        return orderTime.getSpotId();
//    }
//
//    public void setSpotId(int spotId) {
//        this.orderTime.setSpotId(spotId);
//    }
//
//    public String getSpotName() {
//        return orderTime.getSpotName();
//    }
//
//    public void setSpotName(String spotName) {
//        orderTime.setSpotName(spotName);
//    }
//
//
//    public String getStartTime() {
//        return orderTime.getStartTime();
//    }
//
//    public void setStartTime(String startTime) {
//        orderTime.setStartTime(startTime);
//    }
//
//    public String getEndTime() {
//        return orderTime.getEndTime();
//    }
//
//    public void setEndTime(String endTime) {
//        orderTime.setEndTime(endTime);
//    }
//
//    public int getSpotOrderTimeId() { return orderTime.getSpotOrderTimeId();}
//
//    public void setSpotOrderTimeId(int spotOrderTimeId) { orderTime.setSpotOrderTimeId(spotOrderTimeId);}

    public void formatTime(){
        this.orderTime.formatTime();
    }

    public SpotOrderTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(SpotOrderTime orderTime) {
        this.orderTime = orderTime;
    }
}
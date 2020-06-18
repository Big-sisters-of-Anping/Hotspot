package com.example.demo.entity;

import java.util.List;

/**
 * 类名称: Park
 * 类描述: 园区实体类
 *
 * @author: wqy
 * 创建时间: 2020/6/18 5:59 下午
 * Version 1.0
 */

public class Park {
    private Integer parkId;
    private String parkName;
    private List<Spot> spots;

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }
}
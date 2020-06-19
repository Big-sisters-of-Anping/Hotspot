package com.example.demo.entity;

/**
 * 类名称: User
 * 类描述: 用户实体类
 *
 * @author: wqy
 * 创建时间: 2020/6/16 5:59 下午
 * Version 1.0
 */

public class User {

    private Integer userId;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
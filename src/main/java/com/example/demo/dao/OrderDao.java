package com.example.demo.dao;

import com.example.demo.entity.Order;
import com.example.demo.entity.SpotOrderTime;

import java.sql.Date;
import java.util.List;

/**
 * 类名称: OrderDao
 * 类描述: Order数据库调用接口
 *
 * @author: wqy
 * 创建时间: 2020/6/19 2:42 下午
 * Version 1.0
 */
public interface OrderDao {
    List<Order> listAllOrders();
    List<Order> listUserOrders(int userId);
    List<Order> listSpotOrders(int spotId);
    List<SpotOrderTime> listSpotOrderTime(int spotId, Date orderDate);
    int insertOrder(Order order);
    int deleteOrder(int orderId);
    int cancelOrder(int orderId);
    int agreeOrder(int orderId);
    int disagreeOrder(int orderId);
}

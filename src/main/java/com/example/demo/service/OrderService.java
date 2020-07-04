package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.SpotOrderTime;

import java.sql.Date;
import java.util.List;

/**
 * 类名称: OrderService
 * 类描述: 预约服务接口
 *
 * @author: wqy
 * 创建时间: 2020/6/19 2:56 下午
 * Version 1.0
 */
public interface OrderService {
    List<Order> listAllOrders();
    List<Order> listUserOrders(int userId);
    List<Order> listSpotOrders(int spotId);
    List<Order> listOrdersBySpotTime(int spotOrderTimeId, Date date);
    List<Order> listOrdersOfTheseThreeDays(int spotId, int orderStatus);
    List<SpotOrderTime> listSpotOrderTime(int spotId, Date date);
    int insertOrder(Order order);
    boolean deleteOrder(int orderId);
    boolean cancelOrder(int orderId);
    boolean agreeOrder(int orderId);
    boolean disagreeOrder(int orderId);
    boolean checkOrderPeople(int orderId, Date orderDate);
    int insertAndCheckOrder(Order order);
}

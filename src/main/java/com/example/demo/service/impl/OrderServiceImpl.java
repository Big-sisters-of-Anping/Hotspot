package com.example.demo.service.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.SpotOrderTime;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名称: OrderServiceImpl
 * 类描述: 预约服务类实现
 *
 * @author: wqy
 * 创建时间: 2020/6/19 2:56 下午
 * Version 1.0
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> listAllOrders() {
        List<Order> result = orderDao.listAllOrders();
        for (int i = 0; i < result.size(); ++i){
            result.get(i).formatTime();
        }
        return result;
    }

    @Override
    public List<Order> listUserOrders(int userId) {
        List<Order> result = orderDao.listUserOrders(userId);
        for (int i = 0; i < result.size(); ++i){
            result.get(i).formatTime();
            SpotOrderTime curr = result.get(i).getOrderTime();
            curr.setOrderedPeople(orderDao.getOrderedPeopleByOrderTimeId(curr.getSpotOrderTimeId(), result.get(i).getOrderDate()));
        }
        return result;
    }

    @Override
    public List<Order> listSpotOrders(int spotId) {
        List<Order> result = orderDao.listSpotOrders(spotId);
        for (int i = 0; i < result.size(); ++i){
            result.get(i).formatTime();
        }
        return result;
    }

    @Override
    public List<Order> listOrdersBySpotTime(int spotOrderTimeId, Date date) {
        return orderDao.listOrdersBySpotTime(spotOrderTimeId, date);
    }

    @Override
    public List<Order> listOrdersOfTheseThreeDays(int spotId, int orderStatus) {
        Date endDate = new Date(System.currentTimeMillis());
        Date startDate = java.sql.Date.valueOf(LocalDate.now().minusDays(2));
        System.out.println(startDate + "-" + endDate);
        return orderDao.listOrdersOfRangeDays(spotId, orderStatus, startDate, endDate);
    }

    @Override
    public List<SpotOrderTime> listSpotOrderTime(int spotId, Date date) {
        List<SpotOrderTime> result = orderDao.listSpotOrderTime(spotId,date);
        for (int i = 0; i < result.size(); ++i){
            result.get(i).formatTime();
        }
        return result;
    }

    @Override
    public List<SpotOrderTime> listOrderedPeople(int spotId, Date startDate, Date endDate, int orderStatus) {
        List<SpotOrderTime> result;
        if (orderStatus != 5) {
            result = orderDao.listOrderedPeople(spotId, startDate, endDate, orderStatus);
        }
        else{
            result = orderDao.listAllOrderedPeople(spotId, startDate, endDate);
        }
        for (int i = 0; i < result.size(); ++i){
            result.get(i).formatTime();
        }
        return result;
    }

    @Override
    public List<List<SpotOrderTime>> listOrderedPeopleOfAllStatus(int spotId, Date startDate, Date endDate) {
        List<List<SpotOrderTime>> result = new ArrayList<List<SpotOrderTime>>();
        result.add(listOrderedPeople(spotId, startDate, endDate, 0));
        result.add(listOrderedPeople(spotId, startDate, endDate, 1));
        result.add(listOrderedPeople(spotId, startDate, endDate, 2));
        result.add(listOrderedPeople(spotId, startDate, endDate, 5));
        return result;
    }

    @Override
    public List<Order> listDailyOrderedPeople(int spotId, Date startDate, Date endDate, int orderStatus) {
        List<Order> result;
        if (orderStatus != 5)
            result = orderDao.listDailyOrderedPeople(spotId, startDate, endDate, orderStatus);
        else {
            result = orderDao.listDailyAllOrderedPeople(spotId, startDate, endDate);
        }
        LocalDate t = startDate.toLocalDate();
        int index = 0;
        while (java.sql.Date.valueOf(t).before(endDate)){
            if (result.size() <= index || java.sql.Date.valueOf(t).compareTo(result.get(index).getOrderDate()) != 0){
                Order curr = new Order();
                curr.setOrderDate(java.sql.Date.valueOf(t));
                curr.getOrderTime().setOrderedPeople(0);
                result.add(index, curr);
            }
            t = t.plusDays(1);
            ++index;
        }
        return result;
    }

    @Override
    public List<List<Order>> listDailyOrderedPeopleOfAllStatus(int spotId, Date startDate, Date endDate) {
        List<List<Order>> result = new ArrayList<List<Order>>() ;
        result.add(listDailyOrderedPeople(spotId, startDate, endDate, 0));
        result.add(listDailyOrderedPeople(spotId, startDate, endDate, 1));
        result.add(listDailyOrderedPeople(spotId, startDate, endDate, 2));
        result.add(listDailyOrderedPeople(spotId, startDate, endDate, 5));
        return result;
    }

    @Transactional
    @Override
    public int insertOrder(Order order) {
        try {
            int effectedNum = orderDao.insertOrder(order);
            if (effectedNum > 0)
                return order.getOrderId();
            else
                throw new RuntimeException("插入预约失败！");
        }catch (Exception e){
            throw new RuntimeException("插入预约失败：" + e.getMessage());
        }
    }

    @Override
    public boolean checkOrderPeople(int orderId, Date orderDate) {
        return 1 == orderDao.getOrderedPeopleByOrderId(orderId, orderDate);
    }

    @Transactional
    @Override
    public int insertAndCheckOrder(Order order) {
        try {
            int effectedNum = orderDao.insertOrder(order);
            if (effectedNum > 0){
                int orderId = order.getOrderId();
                if (checkOrderPeople(orderId, order.getOrderDate())){
                    effectedNum = orderDao.agreeOrder(orderId);
                    if (effectedNum > 0)
                        return orderId;
                    else
                        throw new RuntimeException("预约失败！通过预约失败！");
                }else {
                    throw new RuntimeException("预约失败！已达到预约人数上线！");
                }
            }
            else
                throw new RuntimeException("插入预约失败！");
        }catch (Exception e){
            throw new RuntimeException("插入预约失败：" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteOrder(int orderId) {
        try {
            int effectedNum = orderDao.deleteOrder(orderId);
            if (effectedNum > 0)
                return true;
            else
                throw new RuntimeException("删除预约失败！");
        }catch (Exception e){
            throw new RuntimeException("删除预约失败：" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean cancelOrder(int orderId) {
        try {
            int effectedNum = orderDao.cancelOrder(orderId);
            if (effectedNum > 0)
                return true;
            else
                throw new RuntimeException("取消预约失败！");
        }catch (Exception e){
            throw new RuntimeException("取消预约失败：" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean agreeOrder(int orderId) {
        try {
            int effectedNum = orderDao.agreeOrder(orderId);
            if (effectedNum > 0)
                return true;
            else
                throw new RuntimeException("通过预约失败！");
        }catch (Exception e){
            throw new RuntimeException("通过预约失败：" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean disagreeOrder(int orderId) {
        try {
            int effectedNum = orderDao.disagreeOrder(orderId);
            if (effectedNum > 0)
                return true;
            else
                throw new RuntimeException("拒绝预约失败！");
        }catch (Exception e){
            throw new RuntimeException("拒绝预约失败：" + e.getMessage());
        }
    }

    @Override
    public int getOrderedPeople(int spotOrderTimeId, Date date) {
        return orderDao.getOrderedPeopleByOrderTimeId(spotOrderTimeId, date);
    }
}
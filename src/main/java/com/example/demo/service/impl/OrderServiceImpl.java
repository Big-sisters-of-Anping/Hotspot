package com.example.demo.service.impl;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
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
        return orderDao.listAllOrders();
    }

    @Override
    public List<Order> listUserOrders(int userId) {
        return orderDao.listUserOrders(userId);
    }

    @Override
    public List<Order> listSpotOrders(int spotId) {
        return orderDao.listSpotOrders(spotId);
    }

    @Transactional
    @Override
    public int insertOrder(Order order) {
        if (order.getStartTime() != null && order.getEndTime() != null){
            try {
                int effectedNum = orderDao.insertOrder(order);
                if (effectedNum > 0)
                    return order.getOrderId();
                else
                    throw new RuntimeException("插入预约失败！");
            }catch (Exception e){
                throw new RuntimeException("插入预约失败：" + e.getMessage());
            }
        }else {
            throw new RuntimeException("未定义的预约时间！");
        }
    }

//    @Transactional
//    @Override
//    public boolean updateOrder(Order order) {
//        if (order.getStartTime() != null && order.getEndTime() != null && order.getStartTime().before(order.getEndTime())){
//            try {
//                int effectedNum = orderDao.updateOrder(order);
//                if (effectedNum > 0)
//                    return true;
//                else
//                    throw new RuntimeException("更新预约失败！");
//            }catch (Exception e){
//                throw new RuntimeException("更新预约失败：" + e.getMessage());
//            }
//        }else {
//            throw new RuntimeException("未定义/不符合逻辑的预约时间！");
//        }
//    }

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
}
package com.example.demo.service.impl;

import com.example.demo.dao.ParkDao;
import com.example.demo.entity.Park;
import com.example.demo.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类名称: ParkServiceImpl
 * 类描述: Park服务实现类
 *
 * @author: wqy
 * 创建时间: 2020/6/18 6:28 下午
 * Version 1.0
 */

@Service
public class ParkServiceImpl implements ParkService {
    @Autowired
    private ParkDao parkDao;

    @Transactional
    @Override
    public boolean addUserToPark(String userId, int parkId) {
        if (userId != null && !"".equals(userId)) {
            try {
                int effectedNum = parkDao.addUserToPark(userId, parkId);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("用户添加园区失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("用户添加园区失败：" + e.getMessage());
            }
        }else {
            throw new RuntimeException("用户Id不能为空！");
        }
    }

    @Override
    public List<Park> getUserPark(String userId) {
        return parkDao.getUserPark(userId);
    }

    @Override
    public List<Park> listAllParks() {
        return parkDao.listAllParks();
    }

    @Transactional
    @Override
    public boolean removeUserFromPark(String userId, int parkId) {
        if (userId != null && !"".equals(userId)){
            try {
                int effectedNum = parkDao.removeUserFromPark(userId, parkId);
                if (effectedNum > 0)
                    return true;
                else {
                    throw new RuntimeException("为用户移除园区失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("为用户移除园区失败：" + e.getMessage());
            }
        }
        else {
            throw new RuntimeException("用户Id不能为空！");
        }
    }
}
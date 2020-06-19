package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 类名称: LoginServiceImpl
 * 类描述:
 *
 * @author: wqy
 * 创建时间: 2020/6/16 5:31 下午
 * Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public String signIn(int userId) {
        try {
            User user = userDao.findUserById(userId);
            if (user != null) {
                return TokenUtil.GetToken(userId);
            }else
                throw new RuntimeException("未注册的用户！");
        }catch (Exception e){
            throw new RuntimeException("未注册的用户：" + e.getMessage());
        }
    }

    @Transactional
    @Override
    public HashMap<String, String> signUp(User user) {
        if (user.getUserName() != null && !"".equals(user.getUserName())) {
            try {
                int effectedNum = userDao.addUser(user);
                if (effectedNum <= 0) {
                    throw new RuntimeException("注册用户失败！");
                }else {
                    HashMap<String, String> result= new HashMap<String, String>();
                    result.put("token", TokenUtil.GetToken(user.getUserId()));
                    result.put("userId", user.getUserId().toString());
                    return result;
                }
            }catch (Exception e){
                throw new RuntimeException("注册用户失败：" + e.getMessage());
            }
        }else {
            throw new RuntimeException("用户名不能为空！");
        }
    }

    @Override
    public boolean exit(int userId) {
        return TokenUtil.RemoveToken(userId);
    }

    @Override
    public User findUserById(int userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public List<User> listAllUsers() {
        return userDao.listAllUsers();
    }
}
package com.example.demo.service.impl;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import com.example.demo.util.TokenUtil;
import com.sun.deploy.security.SelectableSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 类名称: LoginServiceImpl
 * 类描述:
 *
 * @author: wqy
 * 创建时间: 2020/6/16 5:31 下午
 * Version 1.0
 */

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public String SignIn(String userId) {
        if (userId != null && !"".equals(userId)) {
            try {
                String db_user = userDao.existUser(userId);
                if (db_user.equals(userId)) {
                    return TokenUtil.GetToken(userId);
                }else
                    throw new RuntimeException("未注册的用户！");
            }catch (Exception e){
                throw new RuntimeException("未注册的用户：" + e.getMessage());
            }
        }else {
            throw new RuntimeException("用户Id不能为空！");
        }
    }

    @Transactional
    @Override
    public String SignUp(User user) {
        if (user.getUserId() != null && !"".equals(user.getUserId())) {
            try {
                int effectedNum = userDao.AddUser(user);
                if (effectedNum <= 0) {
                    throw new RuntimeException("注册用户失败！");
                }else
                    return TokenUtil.GetToken(user.getUserId());
            }catch (Exception e){
                throw new RuntimeException("注册用户失败：" + e.getMessage());
            }
        }else {
            throw new RuntimeException("用户Id不能为空！");
        }
    }

    @Override
    public boolean Exit(String userId) {
//        if (TokenUtil.FindTokenByUser(userId) && TokenUtil.GetToken(userId).equals(userToken)){
            return TokenUtil.RemoveToken(userId);
//        }
//        else
//            return false;
    }
}
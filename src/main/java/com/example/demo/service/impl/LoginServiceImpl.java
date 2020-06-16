package com.example.demo.service.impl;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import com.example.demo.util.TokenUtil;
import org.springframework.stereotype.Service;

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

    @Override
    public String SignIn(String userId) {
        return TokenUtil.GetToken(userId);
    }

    @Override
    public String SignUp(User user) {
        return TokenUtil.GetToken(user.getUserId());
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
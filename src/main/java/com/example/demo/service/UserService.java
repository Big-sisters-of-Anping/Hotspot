package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.HashMap;
import java.util.List;

/**
 * 类名称: LoginService
 * 类描述: 用户登陆、注册、登出接口
 *
 * @author: wqy
 * 创建时间: 2020/6/16 6:01 下午
 * Version 1.0
 */

public interface UserService {
    String signIn(int userId);
    HashMap<String, String> signUp(User user);
    boolean exit(int userId);
    User findUserById(int userId);
    User findUserByName(String userName);
    List<User> listAllUsers();
}

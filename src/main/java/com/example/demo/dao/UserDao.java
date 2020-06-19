package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;

/**
 * 类名称: UserDao
 * 类描述: User数据库调用接口
 *
 * @author: wqy
 * 创建时间: 2020/6/18 6:46 下午
 * Version 1.0
 */
public interface UserDao {
    int addUser(User user);
    User findUserById(int userId);
    User findUserByName(String userName);
    List<User> listAllUsers();
}

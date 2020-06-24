package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 类名称: LoginController
 * 类描述: 管理用户注册、登陆和登出
 *
 * @author: wqy
 * 创建时间: 2020/6/16 5:23 下午
 * Version 1.0
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器") // for swagger
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登陆", notes = "返回Token，其他函数(除非特殊说明)需要在Cookie中携带Token才能通过权限验证\n特殊说明：本函数不需要Token验证")  // for swagger
    @GetMapping(value = "/signin")
    @TokenLimit(CheckToken=false)
    private String signIn(int userId){
        return userService.signIn(userId);
    }

    @ApiOperation(value = "用户注册", notes = "需要传入除userId之外的信息\n向数据库注册用户并返回Token和用户唯一Id，其他函数(除非特殊说明)需要在Cookie中携带Token才能通过权限验证\n特殊说明：本函数不需要Token验证")
    @PostMapping(value = "/signup")
    @TokenLimit(CheckToken=false)
    private HashMap<String, String> signUp(@RequestBody User user){
        return userService.signUp(user);
    }

    @ApiOperation(value = "用户退出登陆", notes = "注销本次登陆，即注销当前Token\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @GetMapping(value = "/exit")
    @TokenLimit(CheckToken=false)
    private boolean exit(int userId){
        return userService.exit(userId);
    }

    @ApiOperation(value = "根据用户Id查询用户", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @GetMapping(value = "/findUserById")
    @TokenLimit(CheckToken=false)
    private User findUserById(int userId){
        return userService.findUserById(userId);
    }

    @ApiOperation(value = "根据用户name查询用户", notes = "userName是唯一的\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @GetMapping(value = "/findUserByName")
    @TokenLimit(CheckToken=false)
    private User findUserByName(String userName){
        return userService.findUserByName(userName);
    }

    @ApiOperation(value = "列出所有用户", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @GetMapping(value = "/listAllUsers")
    @TokenLimit(CheckToken=false)
    private List<User> listAllUsers(){
        return userService.listAllUsers();
    }
}
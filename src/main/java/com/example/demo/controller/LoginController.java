package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "用户登陆控制器") // for swagger
public class LoginController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "用户登陆", notes = "返回Token，其他函数(除非特殊说明)需要在Cookie中携带Token才能通过权限验证\n特殊说明：本函数不需要Token验证")  // for swagger
    @GetMapping(value = "/signin")
    @TokenLimit(CheckToken=false)
    private String SignIn(String userId){
        return loginService.SignIn(userId);
    }

    @ApiOperation(value = "用户注册", notes = "向数据库注册用户并返回Token，其他函数(除非特殊说明)需要在Cookie中携带Token才能通过权限验证\n特殊说明：本函数不需要Token验证")
    @PostMapping(value = "/signup")
    @TokenLimit(CheckToken=false)
    private String SignUp(@RequestBody User user){
        return loginService.SignUp(user);
    }

    @ApiOperation(value = "用户退出登陆", notes = "注销本次登陆，即注销当前Token \n注意：本函数需要Token验证")
    @GetMapping(value = "/exit")
    private boolean SignUp(String userId){
        return loginService.Exit(userId);
    }
}
package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.Park;
import com.example.demo.entity.User;
import com.example.demo.service.ParkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类名称: ParkController
 * 类描述: 园区
 *
 * @author: wqy
 * 创建时间: 2020/6/18 7:21 下午
 * Version 1.0
 */

@RestController
@RequestMapping("/park")
@Api(tags = "园区控制器") // for swagger
public class ParkController {
    @Autowired
    private ParkService parkService;

    @ApiOperation(value = "为用户添加园区", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/addUserToPark")
    @TokenLimit(CheckToken = false)
    private boolean addUserToPark(String userId, int parkId){
        return parkService.addUserToPark(userId, parkId);
    }

    @ApiOperation(value = "获取用户的园区", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/getUserPark")
    @TokenLimit(CheckToken = false)
    private List<Park> getUserPark(String userId){
        return parkService.getUserPark(userId);
    }

    @ApiOperation(value = "获取所有园区", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listAllParks")
    @TokenLimit(CheckToken = false)
    private List<Park> listAllParks(){
        return parkService.listAllParks();
    }

    @ApiOperation(value = "移除用户的园区", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @DeleteMapping(value = "/removeUserFromPark")
    @TokenLimit(CheckToken = false)
    private boolean removeUserFromPark(String userId, int parkId) {
        return parkService.removeUserFromPark(userId, parkId);
    }
}
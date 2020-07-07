package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.SpotWishTime;
import com.example.demo.entity.Wish;
import com.example.demo.service.WishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * 类名称: WishController
 * 类描述: 想去 控制器
 *
 * @author: wqy
 * 创建时间: 2020/7/2 9:34 下午
 * Version 1.0
 */

@RestController
@RequestMapping("/wish")
@Api(tags = "\"想去\"控制器") // for swagger
public class WishController {
    @Autowired
    private WishService wishService;

    @ApiOperation(value = "列出所有想去", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listAllWishes")
    @TokenLimit(CheckToken = false)
    private List<Wish> listAllWishes(){
        return wishService.listAllWishes();
    }

    @ApiOperation(value = "列出用户的所有想去", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listUserWishes")
    @TokenLimit(CheckToken = false)
    List<Wish> listUserWishes(int userId){
        return wishService.listUserWishes(userId);
    }

    @ApiOperation(value = "列出某地的想去时间段", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listSpotWishTime")
    @TokenLimit(CheckToken = false)
    List<SpotWishTime> listSpotWishTime(int spotId, Date date){
        return wishService.listSpotWishTime(spotId, date);
    }

    @ApiOperation(value = "添加想去", notes = "需要传入：userId, spotWishTimeId, wishDate\n添加成功则返回wishId\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @PostMapping(value = "/addWish")
    @TokenLimit(CheckToken = false)
    int addWish(@RequestBody Wish wish){
        return wishService.insertWish(wish);
    }

    @ApiOperation(value = "取消想去", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @PostMapping(value = "/cancelWish")
    @TokenLimit(CheckToken = false)
    boolean cancelWish(int wishId){
        return wishService.cancelWish(wishId);
    }
}
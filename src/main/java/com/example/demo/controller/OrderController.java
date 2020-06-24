package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.Order;
import com.example.demo.entity.SpotOrderTime;
import com.example.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 类名称: OrderController
 * 类描述: 预约接口
 *
 * @author: wqy
 * 创建时间: 2020/6/19 3:06 下午
 * Version 1.0
 */

@RestController
@RequestMapping("/order")
@Api(tags = "预约控制器") // for swagger
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "列出所有预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listAllOrders")
    @TokenLimit(CheckToken = false)
    private List<Order> listAllOrders(){
        return orderService.listAllOrders();
    }

    @ApiOperation(value = "列出用户的预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listUserOrders")
    @TokenLimit(CheckToken = false)
    private List<Order> listUserOrders(int userId){
        return orderService.listUserOrders(userId);
    }

    @ApiOperation(value = "列出地点的预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listSpotOrders")
    @TokenLimit(CheckToken = false)
    private List<Order> listSpotOrders(int spotId){
        return orderService.listSpotOrders(spotId);
    }

    @ApiOperation(value = "列出某地某天的预约时间列表", notes = "注意：日期格式为\"yyyy-mm-dd\"\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @GetMapping(value = "/listSpotOrderTime")
    @TokenLimit(CheckToken = false)
    private List<SpotOrderTime> listSpotOrderTime(int spotId, Date date) {
        return orderService.listSpotOrderTime(spotId, date);
    }

    @ApiOperation(value = "新增预约", notes = "返回预约Id\n需要传入userId、orderDate、spotOrderTimeId、note(可为空字符串)\n注意：日期格式为\"yyyy-mm-dd\"\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @PostMapping(value = "/addOrder")
    @TokenLimit(CheckToken = false)
    private int addOrder(@RequestBody Order order){
        return orderService.insertOrder(order);
    }

    @ApiOperation(value = "新增预约（先到先得）", notes = "新增预约并且如果当前已预约人数小于预约人数上限则直接通过预约，若预约成功则返回orderId\n需要传入userId、orderDate、spotOrderTimeId、note(可为空字符串)\n注意：日期格式为\"yyyy-mm-dd\"\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @PostMapping(value = "/addFIFOOrder")
    @TokenLimit(CheckToken = false)
    private int addFIFOOrder(@RequestBody Order order){
        return orderService.insertAndCheckOrder(order);
    }

    @ApiOperation(value = "删除预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @DeleteMapping(value = "/deleteOrder")
    @TokenLimit(CheckToken = false)
    private boolean deleteOrder(int orderId){
        return orderService.deleteOrder(orderId);
    }

    @ApiOperation(value = "取消预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/cancelOrder")
    @TokenLimit(CheckToken = false)
    private boolean cancelOrder(int orderId){
        return orderService.cancelOrder(orderId);
    }

    @ApiOperation(value = "通过预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/agreeOrder")
    @TokenLimit(CheckToken = false)
    private boolean agreeOrder(int orderId){
        return orderService.agreeOrder(orderId);
    }

    @ApiOperation(value = "拒绝预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/disagreeOrder")
    @TokenLimit(CheckToken = false)
    private boolean disagreeOrder(int orderId){
        return orderService.disagreeOrder(orderId);
    }
}
package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "新增预约", notes = "返回预约Id\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @PostMapping(value = "/addOrder")
    @TokenLimit(CheckToken = false)
    private int addOrder(@RequestBody Order order){
        return orderService.insertOrder(order);
    }

    @ApiOperation(value = "更新预约", notes = "仅能够更新预约时间\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @PostMapping(value = "/updateOrder")
    @TokenLimit(CheckToken = false)
    private boolean updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }

    @ApiOperation(value = "取消预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @DeleteMapping(value = "/deleteOrder")
    @TokenLimit(CheckToken = false)
    private boolean deleteOrder(int orderId){
        return orderService.deleteOrder(orderId);
    }
}
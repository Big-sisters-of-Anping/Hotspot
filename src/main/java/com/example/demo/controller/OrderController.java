package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.Order;
import com.example.demo.entity.Spot;
import com.example.demo.entity.SpotOrderTime;
import com.example.demo.service.OrderService;
import com.example.demo.service.SpotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

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
    @Autowired
    private SpotService spotService;

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

    @ApiOperation(value = "列出某地某天的预约时间列表", notes = "返回字段：orderId, userId, orderStatus （按订单生成时间升序排列）\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @ApiImplicitParam(name = "date", value = "预约日期\n日期格式为\"yyyy-mm-dd\"")
    @GetMapping(value = "/listSpotOrderTime")
    @TokenLimit(CheckToken = false)
    private List<SpotOrderTime> listSpotOrderTime(int spotId, Date date) {
        return orderService.listSpotOrderTime(spotId, date);
    }

    @ApiOperation(value = "列出某天某地某时段的所有预约", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @ApiImplicitParam(name = "date", value = "预约日期\n日期格式为\"yyyy-mm-dd\"")
    @GetMapping(value = "/listOrdersBySpotTime")
    @TokenLimit(CheckToken = false)
    private List<Order> listOrdersBySpotTime(int spotOrderTimeId, Date date){
        return orderService.listOrdersBySpotTime(spotOrderTimeId, date);
    }

    @ApiOperation(value = "列出最近三天某地的某状态预约", notes = "\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @ApiImplicitParam(value = "预约状态：0-未处理，1-通过，2-未通过", name = "orderStatus")
    @GetMapping(value = "/listOrdersOfLastThreeDays")
    @TokenLimit(CheckToken = false)
    private List<Order> listOrdersOfTheseThreeDays(int spotId, int orderStatus){
        return orderService.listOrdersOfTheseThreeDays(spotId, orderStatus);
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

    @ApiOperation(value = "列出所有可预约地点的可预约信息", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listAllOrderSpotInfo")
    @TokenLimit(CheckToken = false)
    private List<Spot> listAllOrderSpotInfo(){
        List<Spot> tmp = spotService.listAllSpots();
        List<Spot> result = new ArrayList<Spot>();
        for (int i = 0; i < tmp.size(); ++i){
            if (tmp.get(i).getSpotType() == 1){
                List<SpotOrderTime> curr = orderService.listSpotOrderTime(tmp.get(i).getSpotId(), new Date(System.currentTimeMillis()));
                tmp.get(i).setSpotOrderTimeList(curr);
                result.add(tmp.get(i));
            }
        }
        return result;
    }
}
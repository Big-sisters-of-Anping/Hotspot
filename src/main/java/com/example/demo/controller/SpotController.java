package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.Spot;
import com.example.demo.service.SpotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类名称: SpotController
 * 类描述: 地点控制器
 *
 * @author: wqy
 * 创建时间: 2020/6/18 8:59 下午
 * Version 1.0
 */

@RestController
@RequestMapping("/spot")
@Api(tags = "地点控制器")
public class SpotController {
    @Autowired
    private SpotService spotService;

    @ApiOperation(value = "列出所有地点信息", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @GetMapping("/listAllSpots")
    @TokenLimit(CheckToken = false)
    private List<Spot> listAllSpots(){
        return spotService.listAllSpots();
    }

    @ApiOperation(value = "列出某区域内所有地点信息", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @GetMapping("/listAreaSpots")
    @TokenLimit(CheckToken = false)
    private List<Spot> listAreaSpots(double minLongitude, double maxLongitude, double minLatitude, double maxLatitude){
        return spotService.listAreaSpots(minLongitude, maxLongitude, minLatitude, maxLatitude);
    }

    @ApiOperation(value = "通过SpotId查询Spot", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @GetMapping("/querySpotById")
    @TokenLimit(CheckToken = false)
    Spot querySpotById(int spotId){
        return spotService.querySpotById(spotId);
    }

    @ApiOperation(value = "添加地点", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @PostMapping("/addSpot")
    @TokenLimit(CheckToken = false)
    boolean insertSpot(@RequestBody Spot area){
        return spotService.insertSpot(area);
    }
}
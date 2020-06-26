package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.Spot;
import com.example.demo.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.List;

/**
 * 类名称: SearchController
 * 类描述: Search控制器
 *
 * @author: wqy
 * 创建时间: 2020/6/26 10:23 上午
 * Version 1.0
 */

@RestController
@RequestMapping("/search")
@Api(tags = "搜索控制器")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/searchSpotsByName")
    @ApiOperation(value = "根据地点名正则匹配地点，返回所有匹配的地点的列表", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @TokenLimit(CheckToken = false)
    private List<Spot> searchSpotsByName(String spotName){
        return searchService.searchSpotsByName(spotName);
    }

    @GetMapping("/searchSpotsByNameWithLocation")
    @ApiOperation(value = "根据地点名正则匹配地点，返回按距离从近到远排序的地点列表", notes = "特殊说明：测试用，本函数不需要Token验证（实现时将开启）")
    @TokenLimit(CheckToken = false)
    private List<Spot> searchSpotsByNameWithLocation(String spotName, double longitude, double latitude){
        return searchService.searchSpotsByNameWithLocation(spotName, longitude, latitude);
    }
}
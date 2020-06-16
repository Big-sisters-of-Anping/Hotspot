package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.Area;
import com.example.demo.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类名称: AreaController
 * 类描述: controller层：前端调用接口，具体实现在其调用的service层中
 *
 * @author: wqy
 * 创建时间: 2020/5/23 9:30 下午
 * Version 1.0
 */
@RestController
@RequestMapping("/superadmin")
@Api(tags = "例: 区域信息控制器") // for swagger
public class AreaController {
    @Autowired
    private AreaService areaService;

    @ApiOperation(value = "获取区域列表")  // for swagger
    @GetMapping(value = "/listarea")
    @TokenLimit(CheckToken = false)
    private List<Area> listArea(){
        List<Area> list = areaService.getAreaList();
        return list;
    }

    @ApiOperation(value = "根据Id获取区域信息")  // for swagger
    @GetMapping(value = "/getareabyid")
    @TokenLimit(CheckToken = false)
    private Area getAreaById(Integer areaId){
        Area area = areaService.getAreaById(areaId);
        return area;
    }

    @ApiOperation(value = "添加区域信息")  // for swagger
    @PostMapping(value = "/addarea")
    @TokenLimit(CheckToken = false)
    private boolean addArea(@RequestBody Area area){
        return areaService.addArea(area);
    }

    @ApiOperation(value = "修改区域信息")  // for swagger
    @PostMapping(value = "/modifyarea")
    @TokenLimit(CheckToken = false)
    private boolean modifyArea(@RequestBody Area area){
        return areaService.modifyArea(area);
    }

    @ApiOperation(value = "删除区域信息")  // for swagger
    @DeleteMapping(value = "/removearea")  // for route
    @TokenLimit(CheckToken = false)
    private boolean removeArea(Integer areaId){
        return areaService.deleteArea(areaId);
    }
}

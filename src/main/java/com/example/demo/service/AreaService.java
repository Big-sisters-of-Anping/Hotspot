package com.example.demo.service;

import com.example.demo.entity.Area;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * 类名称: AreaService
 * 类描述: 对controller层封装service层的具体操作
 *
 * @author: wqy
 * 创建时间: 2020/5/23 9:03 下午
 * Version 1.0
 */
public interface AreaService {
    List<Area> getAreaList();
    Area getAreaById(int areaId);
    boolean addArea(Area area);
    boolean modifyArea(Area area);
    boolean deleteArea(int areaId);
}

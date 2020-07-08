package com.example.demo.controller;

import com.example.demo.controller.annotation.TokenLimit;
import com.example.demo.entity.Notice;
import com.example.demo.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类名称: NoticeController
 * 类描述: 公告控制类
 *
 * @author: wqy
 * 创建时间: 2020/7/8 9:42 上午
 * Version 1.0
 */

@RestController
@RequestMapping("/notice")
@Api(tags = "公告控制器") // for swagger
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "添加公告", notes = "返回noticeId\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @PostMapping(value = "/addNotice")
    @TokenLimit(CheckToken = false)
    private int addNotice(@RequestBody Notice notice){
        return noticeService.addNotice(notice);
    }

    @ApiOperation(value = "列出所有公告", notes = "按发布时间降序排列\n特殊说明：测试用，本函数不需要Token验证（实现时将开启）")  // for swagger
    @GetMapping(value = "/listNotices")
    @TokenLimit(CheckToken = false)
    private List<Notice> listNotices(){
        return noticeService.listNotices();
    }

}
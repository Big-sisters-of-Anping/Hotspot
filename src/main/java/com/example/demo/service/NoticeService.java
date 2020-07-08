package com.example.demo.service;

import com.example.demo.entity.Notice;

import java.util.List;

/**
 * 类名称: NoticeServiceI
 * 类描述: 公告接口类
 *
 * @author: wqy
 * 创建时间: 2020/7/8 10:05 上午
 * Version 1.0
 */

public interface NoticeService {
    int addNotice(Notice notice);
    List<Notice> listNotices();
}

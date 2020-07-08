package com.example.demo.dao;

import com.example.demo.entity.Notice;

import java.util.List;

/**
 * 类名称: NoticeDao
 * 类描述: 公告数据库调用接口
 *
 * @author: wqy
 * 创建时间: 2020/7/8 10:05 上午
 * Version 1.0
 */

public interface NoticeDao {
    int addNotice(Notice notice);
    List<Notice> listNotices();
}

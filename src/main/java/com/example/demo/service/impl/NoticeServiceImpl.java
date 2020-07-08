package com.example.demo.service.impl;

import com.example.demo.dao.NoticeDao;
import com.example.demo.entity.Notice;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名称: NoticeServiceImpl
 * 类描述: 公告接口实现类
 *
 * @author: wqy
 * 创建时间: 2020/7/8 10:05 上午
 * Version 1.0
 */

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public int addNotice(Notice notice) {
        if (notice.getNoticeTitle() != null && !notice.getNoticeTitle().equals("")){
            try {
                int effectedNum = noticeDao.addNotice(notice);
                if (effectedNum > 0){
                    return notice.getNoticeId();
                }else {
                    throw new RuntimeException("数据库插入失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("添加公告失败："+e.getMessage());
            }
        }else
            throw new RuntimeException("添加公告失败：公告标题不能为空！");
    }

    @Override
    public List<Notice> listNotices() {
        return noticeDao.listNotices();
    }
}
package com.example.demo.entity;

import java.sql.Date;

/**
 * 类名称: Notice
 * 类描述: 公告实体类
 *
 * @author: wqy
 * 创建时间: 2020/7/8 10:02 上午
 * Version 1.0
 */

public class Notice {
    private Integer noticeId;
    private Integer spotId;
    private String spotName;
    private String noticeTitle;
    private String noticeDetail;
    private Date issueDate;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getSpotId() {
        return spotId;
    }

    public void setSpotId(Integer spotId) {
        this.spotId = spotId;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDetail() {
        return noticeDetail;
    }

    public void setNoticeDetail(String noticeDetail) {
        this.noticeDetail = noticeDetail;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
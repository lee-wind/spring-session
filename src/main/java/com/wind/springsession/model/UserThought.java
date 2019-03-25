package com.wind.springsession.model;

import java.util.Date;

public class UserThought {
    private Integer thoughtId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    private Byte status;

    private String thoughtContent;

    public Integer getThoughtId() {
        return thoughtId;
    }

    public void setThoughtId(Integer thoughtId) {
        this.thoughtId = thoughtId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getThoughtContent() {
        return thoughtContent;
    }

    public void setThoughtContent(String thoughtContent) {
        this.thoughtContent = thoughtContent == null ? null : thoughtContent.trim();
    }
}
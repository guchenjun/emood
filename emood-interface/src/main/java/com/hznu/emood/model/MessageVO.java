package com.hznu.emood.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class MessageVO implements Serializable {

    private Long id;

    private String author;

    private String content;

    private Integer thumbsCount;

    private Boolean isVIP;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    public MessageVO() {
    }

    public Integer getThumbsCount() {
        return thumbsCount;
    }

    public void setThumbsCount(Integer thumbsCount) {
        this.thumbsCount = thumbsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getVIP() {
        return isVIP;
    }

    public void setVIP(Boolean VIP) {
        isVIP = VIP;
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", isVIP=" + isVIP +
                ", gmtCreate=" + gmtCreate +
                '}';
    }
}

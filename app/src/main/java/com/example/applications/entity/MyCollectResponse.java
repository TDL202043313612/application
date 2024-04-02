package com.example.applications.entity;

import java.util.List;

public class MyCollectResponse {

    private String msg;
    private int code;
    private List<VideoEntity> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<VideoEntity> getList() {
        return list;
    }

    public void setList(List<VideoEntity> list) {
        this.list = list;
    }

    
}

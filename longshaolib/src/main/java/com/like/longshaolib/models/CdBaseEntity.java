package com.like.longshaolib.models;

import com.google.gson.annotations.Expose;

/**
 * 基础的统一实体类
 * （实际接口数据要根据后台接口提供的统一数据为标准）
 * Created by longshao on 2016/6/20 0020.
 */

public class CdBaseEntity<T> {

//    private int resultCode;
//    private String resultMessage;
//    private T data;
//
//    public int getResultCode() {
//        return resultCode;
//    }
//
//    public void setResultCode(int resultCode) {
//        this.resultCode = resultCode;
//    }
//
//    public String getResultMessage() {
//        return resultMessage;
//    }
//
//    public void setResultMessage(String resultMessage) {
//        this.resultMessage = resultMessage;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }

    @Expose
    private int count;

    @Expose
    private int start;

    @Expose
    private int total;

    @Expose
    private String title;

    @Expose
    private T subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }
}

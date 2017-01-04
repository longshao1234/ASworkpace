package com.like.longshaolib.models;

/**
 * 分组基本实体类
 * Created by longshao on 2016/10/25.
 */

public abstract class CdBaseGroupEntity<T> {

    private boolean isHeader;
    private T t;
    private String header;

    public CdBaseGroupEntity(String header){
        this.isHeader=true;
        this.header=header;
        this.t=null;
    }

    public CdBaseGroupEntity(T t){
        this.isHeader=false;
        this.header=null;
        this.t=t;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}

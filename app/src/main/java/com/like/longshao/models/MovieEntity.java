package com.like.longshao.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2016/6/17 0017.
 */

public class MovieEntity<T> {
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

package com.like.longshao.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2016/6/20 0020.
 */

public class Subject {
    @Expose
    private String title;

    @Expose
    private MoiveImageEntity images;

    public MoiveImageEntity getImages() {
        return images;
    }

    public void setImages(MoiveImageEntity images) {
        this.images = images;
    }

    public Subject(String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

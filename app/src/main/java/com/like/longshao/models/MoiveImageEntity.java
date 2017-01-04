package com.like.longshao.models;

import com.google.gson.annotations.Expose;

/**
 * Created by longshao on 2016/10/25.
 */

public class MoiveImageEntity {
    @Expose
    private String small;
    @Expose
    private String large;
    @Expose
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}

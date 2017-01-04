package com.like.longshao.models;

/**
 * ListFragment content内容实体
 * Created by longshao on 2016/11/16.
 */

public class CdListFiagmentItemEntity {
    private String content;//标题
    private String remark;//备注

    public CdListFiagmentItemEntity(String content,String remark){
        this.content=content;
        this.remark=remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

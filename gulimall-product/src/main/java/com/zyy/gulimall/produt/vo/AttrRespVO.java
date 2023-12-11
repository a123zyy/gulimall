package com.zyy.gulimall.produt.vo;

import lombok.Data;

@Data
public class AttrRespVO extends AttrVO {

    public String catelogName;
    public String groupName;

    public String getCatelogName() {
        return catelogName;
    }

    public void setCatelogName(String catelogName) {
        this.catelogName = catelogName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

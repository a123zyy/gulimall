package com.zyy.gulimall.produt.vo;

import lombok.Data;

@Data
public class BaseAttrs {

    private Long attrId;

    private String attrValues;

    private int showDesc;

    public BaseAttrs() {
    }

    public BaseAttrs(Long attrId, String attrValues, int showDesc) {
        this.attrId = attrId;
        this.attrValues = attrValues;
        this.showDesc = showDesc;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(String attrValues) {
        this.attrValues = attrValues;
    }

    public int getShowDesc() {
        return showDesc;
    }

    public void setShowDesc(int showDesc) {
        this.showDesc = showDesc;
    }
}


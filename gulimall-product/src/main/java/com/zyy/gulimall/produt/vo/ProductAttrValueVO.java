package com.zyy.gulimall.produt.vo;

import lombok.Data;

@Data
public class ProductAttrValueVO {
    /**
     * 商品id
     */
    private Long spuId;
    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 属性值
     */
    private String attrValue;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】
     */
    private Integer quickShow;
}

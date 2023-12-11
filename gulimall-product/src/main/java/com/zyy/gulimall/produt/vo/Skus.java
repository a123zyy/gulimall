package com.zyy.gulimall.produt.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangyangyang
 */
@Data
public class Skus {
    private List<Images> images;

    private  List<BaseAttrs> baseAttrs;

    private List<Attr> attr;

    private BigDecimal price;

    public Skus(List<Images> images, List<BaseAttrs> baseAttrs, List<Attr> attr, BigDecimal price) {
        this.images = images;
        this.baseAttrs = baseAttrs;
        this.attr = attr;
        this.price = price;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<BaseAttrs> getBaseAttrs() {
        return baseAttrs;
    }

    public void setBaseAttrs(List<BaseAttrs> baseAttrs) {
        this.baseAttrs = baseAttrs;
    }

    public List<Attr> getAttr() {
        return attr;
    }

    public void setAttr(List<Attr> attr) {
        this.attr = attr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

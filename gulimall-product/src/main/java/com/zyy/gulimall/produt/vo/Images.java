package com.zyy.gulimall.produt.vo;

import lombok.Data;
@Data
public class Images {

    public String imgUrl;

    public int defaultImg;

    public Images() {
    }

    public Images(String imgUrl, int defaultImg) {
        this.imgUrl = imgUrl;
        this.defaultImg = defaultImg;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(int defaultImg) {
        this.defaultImg = defaultImg;
    }
}

package com.zyy.gulimall.produt.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhangyangyang
 */
@Data
public class SpuSaveVO {


    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String spuName;
    /**
     * 商品描述
     */
    private String spuDescription;
    /**
     * 所属分类id
     */
    private Long catalogId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     *
     */
    private BigDecimal weight;
    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    private Integer publishStatus;

    private List<String> decript;

    private List<Images> images;

    private  List<BaseAttrs> baseAttrs;

    private Bounds bounds;

    private  List<Skus> skus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuDescription() {
        return spuDescription;
    }

    public void setSpuDescription(String spuDescription) {
        this.spuDescription = spuDescription;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public List<String> getDecript() {
        return decript;
    }

    public void setDecript(List<String> decript) {
        this.decript = decript;
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

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public List<Skus> getSkus() {
        return skus;
    }

    public void setSkus(List<Skus> skus) {
        this.skus = skus;
    }
}

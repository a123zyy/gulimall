package com.zyy.gulimall.produt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sku信息
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:51
 */
@Data
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * skuId
	 */
	@TableId
	private Long skuId;
	/**
	 * spuId
	 */
	private Long spuId;
	/**
	 * sku名称
	 */
	private String skuName;
	/**
	 * sku介绍描述
	 */
	private String skuDesc;
	/**
	 * 所属分类id
	 */
	private Long catalogId;
	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 * 默认图片
	 */
	private String skuDefaultImg;
	/**
	 * 标题
	 */
	private String skuTitle;
	/**
	 * 副标题
	 */
	private String skuSubtitle;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 销量
	 */
	private Long saleCount;

	public SkuInfoEntity() {
	}

	public SkuInfoEntity(Long skuId, Long spuId, String skuName, String skuDesc, Long catalogId, Long brandId, String skuDefaultImg, String skuTitle, String skuSubtitle, BigDecimal price, Long saleCount) {
		this.skuId = skuId;
		this.spuId = spuId;
		this.skuName = skuName;
		this.skuDesc = skuDesc;
		this.catalogId = catalogId;
		this.brandId = brandId;
		this.skuDefaultImg = skuDefaultImg;
		this.skuTitle = skuTitle;
		this.skuSubtitle = skuSubtitle;
		this.price = price;
		this.saleCount = saleCount;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSkuDesc() {
		return skuDesc;
	}

	public void setSkuDesc(String skuDesc) {
		this.skuDesc = skuDesc;
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

	public String getSkuDefaultImg() {
		return skuDefaultImg;
	}

	public void setSkuDefaultImg(String skuDefaultImg) {
		this.skuDefaultImg = skuDefaultImg;
	}

	public String getSkuTitle() {
		return skuTitle;
	}

	public void setSkuTitle(String skuTitle) {
		this.skuTitle = skuTitle;
	}

	public String getSkuSubtitle() {
		return skuSubtitle;
	}

	public void setSkuSubtitle(String skuSubtitle) {
		this.skuSubtitle = skuSubtitle;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Long saleCount) {
		this.saleCount = saleCount;
	}
}

package com.zyy.gulimall.produt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sku销售属性&值
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:49
 */
@Data
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * sku_id
	 */
	private Long skuId;
	/**
	 * attr_id
	 */
	private Long attrId;
	/**
	 * 销售属性名
	 */
	private String attrName;
	/**
	 * 销售属性值
	 */
	private String attrValue;
	/**
	 * 顺序
	 */
	private Integer attrSort;

	public SkuSaleAttrValueEntity() {
	}

	public SkuSaleAttrValueEntity(Long id, Long skuId, Long attrId, String attrName, String attrValue, Integer attrSort) {
		this.id = id;
		this.skuId = skuId;
		this.attrId = attrId;
		this.attrName = attrName;
		this.attrValue = attrValue;
		this.attrSort = attrSort;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public Integer getAttrSort() {
		return attrSort;
	}

	public void setAttrSort(Integer attrSort) {
		this.attrSort = attrSort;
	}
}

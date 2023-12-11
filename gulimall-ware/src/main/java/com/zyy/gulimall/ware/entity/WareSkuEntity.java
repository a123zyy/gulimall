package com.zyy.gulimall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品库存
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:50:41
 */
@Data
@TableName("wms_ware_sku")
public class WareSkuEntity implements Serializable {
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
	 * 仓库id
	 */
	private Long wareId;
	/**
	 * 库存数
	 */
	private Integer stock;
	/**
	 * sku_name
	 */
	private String skuName;
	/**
	 * 锁定库存
	 */
	private Integer stockLocked;

	public WareSkuEntity() {
	}

	public WareSkuEntity(Long id, Long skuId, Long wareId, Integer stock, String skuName, Integer stockLocked) {
		this.id = id;
		this.skuId = skuId;
		this.wareId = wareId;
		this.stock = stock;
		this.skuName = skuName;
		this.stockLocked = stockLocked;
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

	public Long getWareId() {
		return wareId;
	}

	public void setWareId(Long wareId) {
		this.wareId = wareId;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Integer getStockLocked() {
		return stockLocked;
	}

	public void setStockLocked(Integer stockLocked) {
		this.stockLocked = stockLocked;
	}
}

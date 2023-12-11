package com.zyy.gulimall.produt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * spu信息介绍
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	private Long spuId;
	/**
	 * 商品介绍
	 */
	private String decript;

	public SpuInfoDescEntity() {
	}

	public SpuInfoDescEntity(Long spuId, String decript) {
		this.spuId = spuId;
		this.decript = decript;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public String getDecript() {
		return decript;
	}

	public void setDecript(String decript) {
		this.decript = decript;
	}
}

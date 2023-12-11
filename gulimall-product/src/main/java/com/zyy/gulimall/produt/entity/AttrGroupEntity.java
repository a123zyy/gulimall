package com.zyy.gulimall.produt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 属性分组
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分组id
	 */
	@TableId
	private Long attrGroupId;
	/**
	 * 组名
	 */
	private String attrGroupName;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 描述
	 */
	private String descript;
	/**
	 * 组图标
	 */
	private String icon;
	/**
	 * 所属分类id
	 */
	private Long catelogId;

    @TableField(exist = false)
	private List<Long> catelogPath;

	public Long getAttrGroupId() {
		return attrGroupId;
	}

	public void setAttrGroupId(Long attrGroupId) {
		this.attrGroupId = attrGroupId;
	}

	public String getAttrGroupName() {
		return attrGroupName;
	}

	public void setAttrGroupName(String attrGroupName) {
		this.attrGroupName = attrGroupName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getCatelogId() {
		return catelogId;
	}

	public void setCatelogId(Long catelogId) {
		this.catelogId = catelogId;
	}

	public List<Long> getCatelogPath() {
		return catelogPath;
	}

	public void setCatelogPath(List<Long> catelogPath) {
		this.catelogPath = catelogPath;
	}
}

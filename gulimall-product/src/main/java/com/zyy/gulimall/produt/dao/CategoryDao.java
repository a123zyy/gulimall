package com.zyy.gulimall.produt.dao;

import com.zyy.gulimall.produt.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:51
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}

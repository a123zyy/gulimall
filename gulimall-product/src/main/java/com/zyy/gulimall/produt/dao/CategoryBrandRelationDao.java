package com.zyy.gulimall.produt.dao;

import com.zyy.gulimall.produt.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 品牌分类关联
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-07-02 15:53:16
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {


}

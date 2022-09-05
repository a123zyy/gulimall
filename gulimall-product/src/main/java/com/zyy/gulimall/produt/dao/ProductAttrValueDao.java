package com.zyy.gulimall.produt.dao;

import com.zyy.gulimall.produt.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spu属性值
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:51
 */
@Mapper
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {



    List<ProductAttrValueEntity> getProductAttrInfoByAttrIDAndSpuId(@Param("attrValueVOS") List<ProductAttrValueEntity> attrValueVOS,@Param("spuId") Long spuId);

    void  insertOrUpdate(List<ProductAttrValueEntity> productAttrValueEntities);
}

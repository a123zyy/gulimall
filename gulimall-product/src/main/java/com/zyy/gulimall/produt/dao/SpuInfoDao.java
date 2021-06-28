package com.zyy.gulimall.produt.dao;

import com.zyy.gulimall.produt.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * spu信息
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    @Select("select * from pms_sku_info where sku_id = #{skuId}")
    SpuInfoEntity selectbyid(@Param("skuId") int skuId);
	
}

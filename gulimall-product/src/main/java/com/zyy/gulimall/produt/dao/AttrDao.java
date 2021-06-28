package com.zyy.gulimall.produt.dao;

import com.zyy.gulimall.produt.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sun.plugin.dom.core.Attr;

/**
 * 商品属性
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {


   @Select("select * from pms_attr where attr_id = #{attrId}")
   Attr selectAllByAttrId(@Param("attrId") String attrId);
	
}

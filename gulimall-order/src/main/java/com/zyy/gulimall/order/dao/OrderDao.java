package com.zyy.gulimall.order.dao;

import com.zyy.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:42:57
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}

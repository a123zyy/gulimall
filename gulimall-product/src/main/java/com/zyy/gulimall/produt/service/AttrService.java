package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


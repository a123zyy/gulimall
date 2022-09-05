package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:51
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils querySkuInfoPage(Map<String, Object> params);
}


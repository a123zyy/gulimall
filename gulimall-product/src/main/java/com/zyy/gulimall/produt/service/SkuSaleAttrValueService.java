package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:49
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


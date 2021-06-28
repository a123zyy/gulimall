package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:51
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


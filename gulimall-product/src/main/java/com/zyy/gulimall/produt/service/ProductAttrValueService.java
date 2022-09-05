package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.ProductAttrValueEntity;
import com.zyy.gulimall.produt.vo.ProductAttrValueVO;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:51
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProductAttrValueEntity> gerAttrValueBySpuId(Long spuId);

    void updateAttrValueBySpuId(List<ProductAttrValueEntity> attrValueVOS, Long spuId);
}


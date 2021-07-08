package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.AttrAttrgroupRelationEntity;
import com.zyy.gulimall.produt.vo.AttrGroupVO;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void batchDelete(List<AttrGroupVO> groupVOS);

    void saveBatch(List<AttrGroupVO> attrGroupVOList);
}


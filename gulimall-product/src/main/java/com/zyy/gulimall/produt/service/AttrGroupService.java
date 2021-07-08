package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.AttrEntity;
import com.zyy.gulimall.produt.entity.AttrGroupEntity;
import com.zyy.gulimall.produt.vo.AttrGroupRespVO;
import com.zyy.gulimall.produt.vo.AttrGroupVO;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageByCategoryId(Map<String, Object> params, Long categoryId);

    List<AttrEntity> selectAttrIdsByAttrgroupId(Long attrgroupId);

    PageUtils selectAttrInfosNotInGroupId( Map<String, Object> params,Long attrgroupId);

    void saveAttrGroup(AttrGroupEntity attrGroup);

    List<AttrGroupRespVO> selectByCatelogId(Long catelogId);
}


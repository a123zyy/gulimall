package com.zyy.gulimall.produt.service.impl;

import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;
import com.zyy.gulimall.produt.vo.AttrGroupVO;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zyy.gulimall.produt.dao.AttrAttrgroupRelationDao;
import com.zyy.gulimall.produt.entity.AttrAttrgroupRelationEntity;
import com.zyy.gulimall.produt.service.AttrAttrgroupRelationService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<AttrGroupVO> groupVOS) {
        List<AttrAttrgroupRelationEntity> collect = groupVOS.stream().map(item -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());

        this.baseMapper.deleteBatchAttrIdsAndAttrGroupIds(collect);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatch(List<AttrGroupVO> attrGroupVOList) {
        if (attrGroupVOList.size() == 0){
            return;
        }
        List<AttrAttrgroupRelationEntity> collect = attrGroupVOList.stream().map(item -> {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}
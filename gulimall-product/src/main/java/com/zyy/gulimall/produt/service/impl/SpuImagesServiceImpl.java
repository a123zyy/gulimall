package com.zyy.gulimall.produt.service.impl;

import com.zyy.gulimall.produt.vo.Images;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.produt.dao.SpuImagesDao;
import com.zyy.gulimall.produt.entity.SpuImagesEntity;
import com.zyy.gulimall.produt.service.SpuImagesService;
import org.springframework.transaction.annotation.Transactional;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImagesEntity> page = this.page(
                new Query<SpuImagesEntity>().getPage(params),
                new QueryWrapper<SpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveImages(Long id, List<Images> images) {
        List<SpuImagesEntity> collect = images.stream().map(item -> {
            SpuImagesEntity imagesEntity = new SpuImagesEntity();
            BeanUtils.copyProperties(item, imagesEntity);
            imagesEntity.setSpuId(id);
            return imagesEntity;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}
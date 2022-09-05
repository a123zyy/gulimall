package com.zyy.gulimall.produt.service.impl;

import com.zyy.gulimall.produt.vo.ProductAttrValueVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.produt.dao.ProductAttrValueDao;
import com.zyy.gulimall.produt.entity.ProductAttrValueEntity;
import com.zyy.gulimall.produt.service.ProductAttrValueService;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Autowired
    ProductAttrValueDao productAttrValueDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ProductAttrValueEntity> gerAttrValueBySpuId(Long spuId) {
        List<ProductAttrValueEntity> attrValueEntities = this.list(new QueryWrapper<ProductAttrValueEntity>().eq("spu_id",spuId));
        return attrValueEntities;
    }



    @Override
    public void updateAttrValueBySpuId(List<ProductAttrValueEntity> attrValueVOS,Long spuId) {
//        List<ProductAttrValueEntity> attrValueEntities = gerAttrValueBySpuId(spuId);


        List<ProductAttrValueEntity> productAttrValueEntities = productAttrValueDao.getProductAttrInfoByAttrIDAndSpuId(attrValueVOS,spuId);
        if (productAttrValueEntities.size() == attrValueVOS.size()){
            productAttrValueEntities.forEach(productAttrValueEntity -> {
                attrValueVOS.forEach(item->{
//
                });
            });
        }




    }



}
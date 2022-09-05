package com.zyy.gulimall.produt.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.produt.dao.SkuInfoDao;
import com.zyy.gulimall.produt.entity.SkuInfoEntity;
import com.zyy.gulimall.produt.service.SkuInfoService;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils querySkuInfoPage(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> skuInfoEntityQueryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)){
            skuInfoEntityQueryWrapper.like("sku_name",key).or().like("sku_desc",key);
        }
        String catelogId = (String) params.get("catelogId");
        if (StringUtils.isNotBlank(catelogId) && !catelogId.equals("0")){
            skuInfoEntityQueryWrapper.eq("catalog_id",catelogId);
        }
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId) && !brandId.equals("0")){
            skuInfoEntityQueryWrapper.eq("brand_id",brandId);
        }
        int max = Integer.parseInt((String) params.get("max"));
        int min = Integer.parseInt((String) params.get("min"));
        if (max>min){
            skuInfoEntityQueryWrapper.between("price",min,max);
        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                skuInfoEntityQueryWrapper
        );
        return new PageUtils(page);
    }

}
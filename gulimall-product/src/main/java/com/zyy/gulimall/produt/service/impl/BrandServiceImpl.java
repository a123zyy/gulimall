package com.zyy.gulimall.produt.service.impl;

import com.zyy.gulimall.produt.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.produt.dao.BrandDao;
import com.zyy.gulimall.produt.entity.BrandEntity;
import com.zyy.gulimall.produt.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)){
            queryWrapper.eq("brand_id",key).or().like("name",key);
        }


        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void updateBrandById(BrandEntity brand) {
        this.baseMapper.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())){
            //TODO 修改名字时关联品牌名字也要改
            categoryBrandRelationService.updatecategoryBrandRelation(brand.getBrandId(),brand.getName());
        }
    }

    @Override
    public void saveBrand(BrandEntity brand) {
        List<BrandEntity> list = list(new QueryWrapper<BrandEntity>().eq("name", brand.getName()).eq("descript", brand.getDescript()).eq("logo", brand.getLogo()));
        if (list.size() == 0){
            this.save(brand);
        }
    }

}
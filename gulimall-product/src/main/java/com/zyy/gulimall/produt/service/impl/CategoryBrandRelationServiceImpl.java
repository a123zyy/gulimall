package com.zyy.gulimall.produt.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zyy.gulimall.produt.dao.CategoryBrandRelationDao;
import com.zyy.gulimall.produt.entity.BrandEntity;
import com.zyy.gulimall.produt.entity.CategoryEntity;
import com.zyy.gulimall.produt.service.BrandService;
import com.zyy.gulimall.produt.service.CategoryBrandRelationService;
import com.zyy.gulimall.produt.service.CategoryService;
import com.zyy.gulimall.produt.vo.CategoryBrandVO;
import org.springframework.beans.BeanUtils;
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


import com.zyy.gulimall.produt.entity.CategoryBrandRelationEntity;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author zyy
 */
@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryBrandRelationEntity> CategoryBrandRelationByBrandId(Long brandId) {
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id",brandId);
        return  this.list(queryWrapper);
    }

    @Override
    public void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation) {
        this.baseMapper.insert(categoryBrandRelation);
    }

    @Override
    public void updatecategoryBrandRelation(Long brangId,String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandId(brangId);
        categoryBrandRelationEntity.setBrandName(name);
        this.baseMapper.update(categoryBrandRelationEntity,new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id",brangId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRelation(CategoryBrandRelationEntity categoryBrandRelation) {
        CategoryEntity categoryEntity = categoryService.getById(categoryBrandRelation.getCatelogId());
        BrandEntity brandEntity = brandService.getById(categoryBrandRelation.getBrandId());
        if (null!=categoryEntity){
            categoryBrandRelation.setCatelogName(categoryEntity.getName());
        }
        if (null!=brandEntity){
            categoryBrandRelation.setBrandName(brandEntity.getName());
        }
        this.baseMapper.insert(categoryBrandRelation);

    }

    @Override
    public List<CategoryBrandVO> selectBycatId(Long catId) {
       return this.list(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id",catId)).stream().map(item->{
            CategoryBrandVO categoryBrandVO = new CategoryBrandVO();
            BeanUtils.copyProperties(item,categoryBrandVO);
            return categoryBrandVO;
        }).collect(Collectors.toList());
    }
}
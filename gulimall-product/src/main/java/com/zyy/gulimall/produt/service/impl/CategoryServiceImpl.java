package com.zyy.gulimall.produt.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zyy.gulimall.produt.WebRequest.CategoryRequest;
import com.zyy.gulimall.produt.entity.CategoryBrandRelationEntity;
import com.zyy.gulimall.produt.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.produt.dao.CategoryDao;
import com.zyy.gulimall.produt.entity.CategoryEntity;
import com.zyy.gulimall.produt.service.CategoryService;


/**
 * @author zyy
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private  CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
       List<CategoryEntity> list = baseMapper.selectList(null);
       List<CategoryEntity> parentCategorys = list.stream().filter(item-> item.getParentCid() == 0).map(category->{
           category.setChildren(getChild(category.getCatId(),list));
           return category;
       }).sorted((menu1,menu2)->{
           return menu1.getSort() -menu2.getSort();
       }).collect(Collectors.toList());
        return parentCategorys;
    }

    @Override
    public List<Long> getCatelogParentById(Long catelogId) {
        List<Long> catelogPath = new ArrayList();
        if (null == catelogId){
            return catelogPath;
        }
        return getCatelogParentById(catelogId,catelogPath);
    }

    @Override
    public void updateCategoryById(CategoryEntity category) {
        this.baseMapper.updateById(category);
        if (!StringUtils.isEmpty(category.getName())){
            CategoryBrandRelationEntity relationEntity = new CategoryBrandRelationEntity();
            relationEntity.setCatelogId(category.getCatId());
            relationEntity.setCatelogName(category.getName());
            categoryBrandRelationService.update(relationEntity,new UpdateWrapper<CategoryBrandRelationEntity>().eq("catelog_id",category.getCatId()
            ));
        }
    }

    private List<Long> getCatelogParentById(Long catelogId,List<Long> catelogPath){
        catelogPath.add(catelogId);
        if ( baseMapper.selectById(catelogId).getParentCid() != 0){
            getCatelogParentById(baseMapper.selectById(catelogId).getParentCid(),catelogPath);
        }
        Collections.reverse(catelogPath);
        return catelogPath;

    }

    public List<CategoryEntity> getChild(Long id,List<CategoryEntity> list){
       return list.stream().filter(item-> item.getParentCid().equals(id)).map(item->{
           item.setChildren(getChild(item.getCatId(),list));
           return item;
       }).sorted((menu1,menu2)->{
            return menu1.getSort() -menu2.getSort();
        }).collect(Collectors.toList());
    }

}
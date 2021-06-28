package com.zyy.gulimall.produt.service.impl;

import com.zyy.gulimall.produt.WebRequest.CategoryRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
       List<CategoryEntity> parentCategorys = list.stream().filter(item-> item.getParentCid() == null).map(item->{
           item.setChildren(getChild(item.getCatId(),list));
           return item;
       }).sorted((menu1,menu2)->{
           return menu1.getSort() -menu2.getSort();
       }).collect(Collectors.toList());
        return parentCategorys;
    }

    public List<CategoryEntity> getChild(Long id,List<CategoryEntity> list){
       return list.stream().filter(item->(item.getParentCid()==null?0:item.getParentCid()) == id).map(item->{
           item.setChildren(getChild(item.getCatId(),list));
           return item;
       }).sorted((menu1,menu2)->{
            return menu1.getSort() -menu2.getSort();
        }).collect(Collectors.toList());
    }

}
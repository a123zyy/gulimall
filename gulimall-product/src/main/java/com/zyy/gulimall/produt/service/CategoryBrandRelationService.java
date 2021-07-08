package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;

import com.zyy.gulimall.produt.entity.CategoryBrandRelationEntity;
import com.zyy.gulimall.produt.vo.CategoryBrandVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-07-02 15:53:16
 */
@Service
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryBrandRelationEntity> CategoryBrandRelationByBrandId(Long brandId);

    void saveCategoryBrandRelation(CategoryBrandRelationEntity categoryBrandRelation);

    void updatecategoryBrandRelation(Long brangId,String name);

    void saveRelation(CategoryBrandRelationEntity categoryBrandRelation);

    List<CategoryBrandVO> selectBycatId(Long catId);
}


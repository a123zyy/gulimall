package com.zyy.gulimall.produt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyy.gulimall.produt.entity.CategoryEntity;
import com.zyy.gulimall.produt.service.CategoryService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.R;



/**
 * 商品三级分类
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:51
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取到三级菜单
     *
     * */
    @RequestMapping("/list/tree")
    public R CategoryList(){
        return R.ok().put("data", categoryService.listWithTree());
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    ////@RequiresPermissions("produt:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    ////@RequiresPermissions("produt:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    ////@RequiresPermissions("produt:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // //@RequiresPermissions("produt:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateCategoryById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    ////@RequiresPermissions("produt:category:delete")
    public R delete(@RequestBody Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

    /**
     * 批量修改排序
     */

    @RequestMapping("update/sort")
    public R updateSort(@RequestBody List<CategoryEntity> list){
        categoryService.updateBatchById(list);
        return R.ok();
    }

}

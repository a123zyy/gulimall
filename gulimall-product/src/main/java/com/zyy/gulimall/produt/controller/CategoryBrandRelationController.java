package com.zyy.gulimall.produt.controller ;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zyy.gulimall.produt.service.BrandService;
import com.zyy.gulimall.produt.service.CategoryBrandRelationService;
import com.zyy.gulimall.produt.service.CategoryService;
import com.zyy.gulimall.produt.vo.CategoryBrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyy.gulimall.produt.entity.CategoryBrandRelationEntity;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.R;




/**
 * 品牌分类关联
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-07-02 15:53:16
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;


    /**
     * 列表
     */
    @GetMapping("/brands/list")
    ////@RequiresPermissions("product:categorybrandrelation:list")
    public R listBycatId(Long catId){
        List<CategoryBrandVO> categoryBrandVOS = categoryBrandRelationService.selectBycatId(catId);

        return R.ok().put("data", categoryBrandVOS);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    ////@RequiresPermissions("product:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/catelog/list")
    ////@RequiresPermissions("product:categorybrandrelation:list")
    public R list(Long brandId){
        return R.ok().put("data",  categoryBrandRelationService.CategoryBrandRelationByBrandId(brandId));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    ////@RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    ////@RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
        //TODO 每次修改关联关系要更新
        categoryBrandRelationService.saveRelation(categoryBrandRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // //@RequiresPermissions("product:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    ////@RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));
		return R.ok();
    }

}

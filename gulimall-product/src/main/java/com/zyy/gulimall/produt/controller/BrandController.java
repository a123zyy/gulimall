package com.zyy.gulimall.produt.controller;

import java.util.Arrays;
import java.util.Map;

////import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.R;
import com.zyy.gulimall.common.valid.AddGroup;
import com.zyy.gulimall.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyy.gulimall.produt.entity.BrandEntity;
import com.zyy.gulimall.produt.service.BrandService;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    ////@RequiresPermissions("produt:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    ////@RequiresPermissions("produt:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    ////@RequiresPermissions("produt:brand:save")
    public R save(@RequestBody @Validated(value = {AddGroup.class}) BrandEntity brand, BindResult bindResult){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // //@RequiresPermissions("produt:brand:update")
    public R update(@RequestBody @Validated(value = {UpdateGroup.class}) BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    ////@RequiresPermissions("produt:brand:delete")
    public R delete(@RequestBody @Valid Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}

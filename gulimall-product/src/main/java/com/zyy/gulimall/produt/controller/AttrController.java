package com.zyy.gulimall.produt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

////import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.R;
import com.zyy.gulimall.produt.service.ProductAttrValueService;
import com.zyy.gulimall.produt.vo.AttrRespVO;
import com.zyy.gulimall.produt.vo.AttrVO;
import com.zyy.gulimall.produt.vo.ProductAttrValueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyy.gulimall.produt.entity.AttrEntity;
import com.zyy.gulimall.produt.service.AttrService;




/**
 * 商品属性
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    ////@RequiresPermissions("produt:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/base/listforspu/{spuId}")
    ////@RequiresPermissions("produt:attr:list")
    public R listforspu(@PathVariable("spuId") Long spuId){
        return R.ok().put("data", productAttrValueService.gerAttrValueBySpuId(spuId));
    }

    /**
     * 规格属性
     * 1.根据type判断查询的是基本属性还是销售属性
     * 2.根据分类id查询具体的属性
     * categoryId = 0 查询所有商品
     * type = base
     */
    @RequestMapping("/{type}/list/{categoryId}")

    ////@RequiresPermissions("produt:attr:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("type") String type,@PathVariable("categoryId") Long categoryId){
        PageUtils page = attrService.queryPageByCategoryId(params,type,categoryId);
        return R.ok().put("page", page);
    }



    /**
     * 根据id获取属性的详细信息
     */
    @RequestMapping("/info/{attrId}")
    ////@RequiresPermissions("produt:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
        AttrVO attr = attrService.getAttrEntityById(attrId);
		return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    ////@RequiresPermissions("produt:attr:save")
    public R save(@RequestBody AttrVO attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // //@RequiresPermissions("produt:attr:update")
    public R update(@RequestBody AttrVO attr){
		attrService.updateAttrEntityById(attr);

        return R.ok();
    }
    @RequestMapping("/update/{spuId}")
    // //@RequiresPermissions("produt:attr:update")
    public R update(@RequestParam List<ProductAttrValueVO> attrValueVOS, @PathVariable("spuId") Long spuId){
//        attrService.updateAttrValueBySpuId(spuId);

        return R.ok();
    }

    /**
     * 删除规格 有关联关系的不删
     */
    @RequestMapping("/delete")
    ////@RequiresPermissions("produt:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
        return attrService.deleteAttrIds(Arrays.asList(attrIds))?R.ok():R.error("部分删除失败,先解除关联关系");
    }

}

package com.zyy.gulimall.produt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.AttrEntity;
import com.zyy.gulimall.produt.service.AttrAttrgroupRelationService;
import com.zyy.gulimall.produt.service.AttrService;
import com.zyy.gulimall.produt.service.CategoryService;
import com.zyy.gulimall.produt.vo.AttrGroupRespVO;
import com.zyy.gulimall.produt.vo.AttrGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyy.gulimall.produt.entity.AttrGroupEntity;
import com.zyy.gulimall.produt.service.AttrGroupService;
import com.zyy.gulimall.common.utils.R;



/**
 * 属性分组
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    AttrService attrService;

    @Autowired
    AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 根据分类id查询属性分组list 再根据分组id查询到对应的属性list
     * */
    @GetMapping("/{catelogId}/withattr")
    ////@RequiresPermissions("produt:attrgroup:list")
    public R saveList(@PathVariable("catelogId") Long catelogId){
        List<AttrGroupRespVO> attrGroupRespVOS = attrGroupService.selectByCatelogId(catelogId);
        return R.ok().put("data", attrGroupRespVOS);
    }


    /**
     * 批量新增属性分组
     * */
    @PostMapping("/attr/relation")
    ////@RequiresPermissions("produt:attrgroup:list")
    public R saveList(@RequestBody List<AttrGroupVO> attrGroupVOList){
        attrAttrgroupRelationService.saveBatch(attrGroupVOList);
        return R.ok();
    }


    /**
     * 列表
     * 1.获取所有属性且不包括已经和attrgroupId关联的属性
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    ////@RequiresPermissions("produt:attrgroup:list")
    public R list(@PathVariable("attrgroupId") Long attrgroupId,@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupService.selectAttrInfosNotInGroupId(params,attrgroupId);
        return R.ok().put("page", page);
    }

    /**
     * 获取已经和attrgroupId关联的所有属性
     * */
    @GetMapping("/{attrgroupId}/attr/relation")
    ////@RequiresPermissions("produt:attrgroup:list")
    public R list(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> attrEntityList =  attrGroupService.selectAttrIdsByAttrgroupId(attrgroupId);

        return R.ok().put("data", attrEntityList);
    }


    /**
     * 属性分为销售属性和规则属性
     * 属性分组是指一个事物的特性 材质 直径 产地
     * 删除属性分组同时删除关联关系
     */
    @PostMapping("/attr/relation/delete")
    ////@RequiresPermissions("produt:attrgroup:list")
    public R list(@RequestBody() List<AttrGroupVO> groupVos){
        attrAttrgroupRelationService.batchDelete(groupVos);
        return R.ok();
    }

    @RequestMapping("/list")
    ////@RequiresPermissions("produt:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupService.queryPage(params);
        return R.ok().put("page", page);
    }



    /**
     * 根据categoryId查询所有的属性
     * */
    @RequestMapping("/list/{categoryId}")
    ////@RequiresPermissions("produt:attrgroup:list")
    public R listByCategoryId(@RequestParam Map<String, Object> params,@PathVariable Long categoryId){
        PageUtils page = attrGroupService.queryPageByCategoryId(params,categoryId);
        return R.ok().put("page", page);
    }


    /**
     * 根据关联的属性分组id查询详细的属性信息
     */
    @RequestMapping("/info/{attrGroupId}")
    ////@RequiresPermissions("produt:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
//        attrGroup.setCatelogPath(categoryService.getCatelogParentById(attrGroup.getCatelogId()));

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    ////@RequiresPermissions("produt:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.saveAttrGroup(attrGroup);
		return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // //@RequiresPermissions("produt:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除属性分组
     */
    @RequestMapping("/delete")
    ////@RequiresPermissions("produt:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
       return attrGroupService.deleteAttrGroupID(Arrays.asList(attrGroupIds)) ? R.ok():R.error("部分已关联数据,未删除");
    }

}

package com.zyy.gulimall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zyy.gulimall.ware.vo.MergeVo;
import com.zyy.gulimall.ware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyy.gulimall.ware.entity.PurchaseEntity;
import com.zyy.gulimall.ware.service.PurchaseService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.R;



/**
 * 采购信息
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:50:40
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    ////@RequiresPermissions("ware:purchase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 未采购的列表
     */
    @RequestMapping("/unreceive/list")
    ////@RequiresPermissions("ware:waresku:list")
    public R unreceiveList(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPageUnreceivePurchase(params);
        return R.ok().put("page", page);
    }


    /**
     * 合并订单
     */
    @RequestMapping("/merge")
    ////@RequiresPermissions("ware:waresku:list")
    public void merge(@RequestBody MergeVo mergeVo){
      purchaseService.purchaseMerge(mergeVo);
    }

    /**
     * 员工系统->员工领取采购单
     * TODO 未测试
     */
    @RequestMapping("/received")
    ////@RequiresPermissions("ware:waresku:list")
    public R received(@RequestBody List<Long> ids){
       purchaseService.received(ids);
       return R.ok();
    }

    /**
     * 员工系统->员工完成采购单
     * TODO 未测试
     */
    @RequestMapping("/done")
    ////@RequiresPermissions("ware:waresku:list")
    public R done(@RequestBody PurchaseDoneVo purchaseDoneVo){
        purchaseService.done(purchaseDoneVo);
        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    ////@RequiresPermissions("ware:purchase:info")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    ////@RequiresPermissions("ware:purchase:save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // //@RequiresPermissions("ware:purchase:update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    ////@RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package com.zyy.gulimall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zyy.gulimall.coupon.entity.CouponEntity;
import com.zyy.gulimall.coupon.service.CouponService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.R;



/**
 * 优惠券信息
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:15:59
 */
@RefreshScope
@RestController
@RequestMapping("")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Value("${coupon.user}")
    public String user;

    @Value("${coupon.age}")
    public String age;

    @GetMapping("/getValue")
    public R getValue(){
        return R.ok().put("user",user).put("age",age);
    }

    @GetMapping("/getCoupon")
    public R getCoupon(){
       CouponEntity couponEntity =  new CouponEntity();
//       couponEntity.setCouponName("小龙虾有优惠啦~");
       return R.ok().put("coupon",Arrays.asList(couponEntity));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    ////@RequiresPermissions("coupon:coupon:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    ////@RequiresPermissions("coupon:coupon:info")
    public R info(@PathVariable("id") Long id){
		CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    ////@RequiresPermissions("coupon:coupon:save")
    public R save(@RequestBody CouponEntity coupon){
		couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // //@RequiresPermissions("coupon:coupon:update")
    public R update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    ////@RequiresPermissions("coupon:coupon:delete")
    public R delete(@RequestBody Long[] ids){
		couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

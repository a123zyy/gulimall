package com.zyy.gulimall.produt.feign;

import com.zyy.gulimall.common.to.SkuReductionTo;
import com.zyy.gulimall.common.to.SpuBoundTo;
import com.zyy.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.DatabaseMetaData;

@FeignClient(value = "gulimall-coupon")
public interface CouponFeignService {
    /**
     * 服务之前调用和保存
     * */
    @PostMapping("/coupon/spubounds/save")
    R saveCouponBound(@RequestBody SpuBoundTo boundTo);

    @RequestMapping("/coupon/skufullreduction/saveInfo")
    ////@RequiresPermissions("coupon:skufullreduction:save")
    R saveSkuReductionTo(@RequestBody SkuReductionTo skuReductionTo);

}

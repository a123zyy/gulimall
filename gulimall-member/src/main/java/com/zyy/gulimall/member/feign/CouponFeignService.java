package com.zyy.gulimall.member.feign;

import com.zyy.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 要远程调用别的服务
 * 1.引入open-feign
 * 2.编写接口 @FeignClient("远程服务名")+具体服务地址
 *
 *
 * */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @RequestMapping("coupon/coupon/getCoupon")
    R getCoupon();

}

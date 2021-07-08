package com.zyy.gulimall.member.feign;

import com.zyy.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1.调用者和被调用者都要在注册中心注册上
 * 2.调用者引入open-feign
 * 3.调用者启动类上加上EnableFeignClients("调用类的指定位置")
 * 4.指定的调用方法上加上 @FeignClient("远程服务名")
 * 5.调用到具体接口
 *
 *
 * */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @RequestMapping("coupon/coupon/getCoupon")
    R getCoupon();

}

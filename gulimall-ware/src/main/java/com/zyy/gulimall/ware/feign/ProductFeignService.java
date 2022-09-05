package com.zyy.gulimall.ware.feign;

import com.zyy.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("gulimall-product")
public interface ProductFeignService {
    /**
     * 信息
     */
    @PostMapping("/product/skuinfo/info/{skuId}")
    R info(@PathVariable("skuId") Long skuId);

    @PostMapping("/product/skuinfo/info/{skuIds}")
    R SkuInfoByIds(List<Long> skuIds);
}

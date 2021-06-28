package com.zyy.gulimall;

import com.zyy.gulimall.coupon.controller.SkuBoundsController;
import com.zyy.gulimall.coupon.entity.SkuLadderEntity;
import com.zyy.gulimall.coupon.service.SkuLadderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GulimallCouponApplicationTests {
    @Autowired
    public SkuLadderService skuLadderService;

    @Test
    void contextLoads() {
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setAddOther(123);
        skuLadderEntity.setSkuId(3L);
        skuLadderService.save(skuLadderEntity);
        System.out.println("更新完毕");
    }

}

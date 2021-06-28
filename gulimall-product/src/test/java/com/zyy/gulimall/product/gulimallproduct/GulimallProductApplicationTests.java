package com.zyy.gulimall.product.gulimallproduct;

import com.zyy.gulimall.produt.ProuctApplication;
import com.zyy.gulimall.produt.entity.BrandEntity;
import com.zyy.gulimall.produt.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试类和启动类不同名需要加classes
 * */
@SpringBootTest(classes = ProuctApplication.class)
class GulimallProductApplicationTests {
    @Autowired
    public BrandService brandService;
	@Test
	void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("测试一条");
        brandEntity.setName("滴滴答答");
        brandEntity.setLogo("234324");
	    brandService.save(brandEntity);
    }

}

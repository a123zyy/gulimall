package com.zyy.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.coupon.entity.SkuLadderEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:15:58
 */
@Service
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


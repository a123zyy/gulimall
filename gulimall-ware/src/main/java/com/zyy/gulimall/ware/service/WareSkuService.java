package com.zyy.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.ware.entity.PurchaseDetailEntity;
import com.zyy.gulimall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:50:41
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryWareSkuPage(Map<String, Object> params);

    void addStock(Long skuId,Long wareId,Integer skuNum);

    void addStocks(List<PurchaseDetailEntity> purchaseDetail);
}


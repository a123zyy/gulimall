package com.zyy.gulimall.ware.service.impl;

import com.zyy.gulimall.common.utils.R;
import com.zyy.gulimall.produt.entity.SkuInfoEntity;
import com.zyy.gulimall.ware.entity.PurchaseDetailEntity;
import com.zyy.gulimall.ware.feign.ProductFeignService;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;
import com.zyy.gulimall.ware.dao.WareSkuDao;
import com.zyy.gulimall.ware.entity.WareSkuEntity;
import com.zyy.gulimall.ware.service.WareSkuService;
import org.springframework.util.CollectionUtils;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryWareSkuPage(Map<String, Object> params) {
        QueryWrapper<WareSkuEntity> wareSkuEntity = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmpty(skuId)){
            wareSkuEntity.eq("sku_id",skuId);
        }
        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)){
            wareSkuEntity.eq("ware_id",wareId);
        }
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wareSkuEntity
        );
        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
       WareSkuEntity wareSkuEntity =  this.getOne(new QueryWrapper<WareSkuEntity>()
               .eq("ware_id",wareId).eq("sku_id",skuId));
       if (wareSkuEntity == null){
           //save
           WareSkuEntity wareSku = new WareSkuEntity();
           wareSku.setSkuId(skuId);
           wareSku.setWareId(wareId);
           wareSku.setStock(skuNum);
           wareSku.setStockLocked(0);
           //查出远程名字
           R r =  productFeignService.info(skuId);
           SkuInfoEntity skuInfo = (SkuInfoEntity) r.getOrDefault("skuInfo",null);
           if (skuInfo != null){
               wareSku.setSkuName(skuInfo.getSkuName());
           }
           this.save(wareSku);
       } else {
           wareSkuEntity.setStock(wareSkuEntity.getStock()+skuNum);
           this.updateById(wareSkuEntity);
       }
    }

    @Override
    public void addStocks(List<PurchaseDetailEntity> purchaseDetail) {
        List<WareSkuEntity> wareSkuEntityArrayList = new ArrayList<WareSkuEntity>();
        List<Long> skuIds = purchaseDetail.stream().map(PurchaseDetailEntity::getSkuId).collect(Collectors.toList());
        //查出远程名字 TODO 接口还没写完
        R r =  productFeignService.SkuInfoByIds(skuIds);
        // 如果查出来是null 就新增一个 如果不为null就老的库存+新增的保存
        purchaseDetail.forEach(purchaseDetailEntity -> {
            WareSkuEntity entity =  this.getOne(new QueryWrapper<WareSkuEntity>()
                    .eq("ware_id",purchaseDetailEntity.getWareId()).eq("sku_id",purchaseDetailEntity.getSkuId()));
            if (entity == null){
                //save
                WareSkuEntity wareSku = new WareSkuEntity();
                wareSku.setSkuId(purchaseDetailEntity.getSkuId());
                wareSku.setWareId(purchaseDetailEntity.getWareId());
                wareSku.setStock(purchaseDetailEntity.getSkuNum());
                wareSku.setStockLocked(0);
                List<SkuInfoEntity> skuInfos = (List<SkuInfoEntity>) r.getOrDefault("skuInfos",null);
                if (!CollectionUtils.isEmpty(skuInfos)){
                   for (SkuInfoEntity skuInfo:skuInfos){
                       if (skuInfo.getSkuId() == purchaseDetailEntity.getSkuId()) {
                           wareSku.setSkuName(skuInfo.getSkuName());
                       }
                   }
                }
                this.save(wareSku);
                wareSkuEntityArrayList.add(wareSku);
            } else {
                entity.setStockLocked(entity.getStockLocked()+purchaseDetailEntity.getSkuNum());
                wareSkuEntityArrayList.add(entity);
            }
        });
        this.saveOrUpdateBatch(wareSkuEntityArrayList);
    }

}
package com.zyy.gulimall.produt.service.impl;

import com.zyy.gulimall.common.to.SkuReductionTo;
import com.zyy.gulimall.common.to.SpuBoundTo;
import com.zyy.gulimall.common.utils.R;
import com.zyy.gulimall.produt.entity.*;
import com.zyy.gulimall.produt.feign.CouponFeignService;
import com.zyy.gulimall.produt.service.*;
import com.zyy.gulimall.produt.vo.*;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.produt.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    SpuImagesService spuImagesService;

    @Autowired
    AttrService attrService;
    @Autowired
    ProductAttrValueService productAttrValueService;

    @Autowired
    SkuImagesService skuImagesService;

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    CouponFeignService couponFeignService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils querySpuInfoPage(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> spuInfoEntityQueryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)){
            spuInfoEntityQueryWrapper.like("spu_name",key).or().like("spu_description",key);
        }
        String catelogId = (String) params.get("catelogId");
        if (catelogId !=null && Integer.parseInt(catelogId) > 0){
            spuInfoEntityQueryWrapper.eq("catalog_id",catelogId);
        }
        String brandId = (String) params.get("brandId");
        if (brandId!=null && Integer.parseInt(brandId) > 0){
            spuInfoEntityQueryWrapper.eq("brand_id",brandId);
        }
        String status = (String) params.get("status");
        if (StringUtils.isNotBlank(status)){
            spuInfoEntityQueryWrapper.eq("publish_status",status);
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                spuInfoEntityQueryWrapper
        );
        return new PageUtils(page);
    }

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(rollbackFor = Exception.class)
    //待校验 -如果其中一个保存方法失败且都加了注解的情况下,抛出异常 那么所有的方法都会回滚吗
    public void saveSpuInfo(SpuSaveVO spuSaveVO) {
           //1保存基本的spu信息 pms_spu_info spuInfo
           SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
           BeanUtils.copyProperties(spuSaveVO,spuInfoEntity);
           spuInfoEntity.setCreateTime(new Date());
           spuInfoEntity.setUpdateTime(new Date());
           this.baseMapper.insert(spuInfoEntity);
           //2.保存Spu的描述图片 spu_info_desc spuInfoDEsc
           SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
           descEntity.setDecript( StringUtils.join(spuSaveVO.getDecript(),","));
           descEntity.setSpuId(spuInfoEntity.getId());
           //3.保存Spu的图片集pms_spu_images SpuImages
           List<Images> images = spuSaveVO.getImages();
           if (images != null && images.size()>0){
               spuImagesService.saveImages(spuInfoEntity.getId(),images);
           }
           //4.保存Spu的规格参数pms_proudct_attr_value baseattr
           List<BaseAttrs> baseAttrs = spuSaveVO.getBaseAttrs();
           if (baseAttrs!=null &&baseAttrs.size()>0){
               List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream().map(item -> {
                   ProductAttrValueEntity attrValueEntity = new ProductAttrValueEntity();
                   attrValueEntity.setAttrId(item.getAttrId());
                   AttrEntity attrEntity = attrService.getById(item.getAttrId());
                   attrValueEntity.setAttrName(attrEntity.getAttrName());
                   attrValueEntity.setQuickShow(item.getShowDesc());
                   attrValueEntity.setAttrValue(item.getAttrValues());
                   attrValueEntity.setSpuId(spuInfoEntity.getId());
                   return attrValueEntity;
               }).collect(Collectors.toList());
               productAttrValueService.saveBatch(productAttrValueEntities);
           }
           //5.保存Spu的积分信息sms->spu_bounds
           Bounds bounds = spuSaveVO.getBounds();
           SpuBoundTo spuBoundTo = new SpuBoundTo();
           BeanUtils.copyProperties(bounds,spuBoundTo);
           spuBoundTo.setSpuId(spuBoundTo.getSpuId());
           //不同服务异步
           R r = couponFeignService.saveCouponBound(spuBoundTo);
           Integer code = (Integer) r.getOrDefault("code",null);
            if (code == null || HttpStatus.SC_INTERNAL_SERVER_ERROR ==Integer.valueOf(code)) {
                new RuntimeException("保存失败");
            }

           //5.保存当前Spu对应的Sku的信息；
           List<Skus> skus = spuSaveVO.getSkus();
           //5.1 sku的基本信息sku_info
           skus.forEach(item->{
               String defaultImg ="";
               //查出这一条sku的默认图片
               List<Images> images1 = item.getImages();
               if (images1!=null &&images1.size()>0){
                   List<Images> collect = images1.stream().filter(images2 -> images2.getDefaultImg() == 1).collect(Collectors.toList());
                   if (collect.size()>0){
                       defaultImg = collect.get(0).getImgUrl();
                   }
               }
               SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
               BeanUtils.copyProperties(item,skuInfoEntity);
               skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
               skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
               skuInfoEntity.setSaleCount(0L);
               skuInfoEntity.setPrice(item.getPrice());
               skuInfoEntity.setSpuId(spuInfoEntity.getId());
               skuInfoEntity.setSkuDefaultImg(defaultImg);
               //保存sku信息
               skuInfoService.save(skuInfoEntity);

               //5.2 sku的基本信息sku_images
               List<SkuImagesEntity> collect = images1.stream().filter((img1)->StringUtils.isBlank(img1.getImgUrl())).map(img -> {
                   SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                   BeanUtils.copyProperties(item, skuImagesEntity);
                   skuImagesEntity.setSkuId(skuInfoEntity.getSkuId());
                   return skuImagesEntity;
               }).collect(Collectors.toList());
               skuImagesService.saveBatch(collect);
               //5.3 sku的销售属性信息sku_sale_attr_value;
               List<Attr> attr = item.getAttr();
               if (attr!=null && attr.size()>0){
                   List<SkuSaleAttrValueEntity> saleAttrValueEntities = attr.stream().map(attr1 -> {
                       SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                       BeanUtils.copyProperties(attr1, skuSaleAttrValueEntity);
                       skuSaleAttrValueEntity.setSkuId(skuInfoEntity.getSkuId());
                       return skuSaleAttrValueEntity;
                   }).collect(Collectors.toList());
                   skuSaleAttrValueService.saveBatch(saleAttrValueEntities);
               }
               //5.4 sku的优惠满减信息sku_ladder suk_mall
               SkuReductionTo skuReductionTo = new SkuReductionTo();
               BeanUtils.copyProperties(item,skuReductionTo);
               skuReductionTo.setSkuId(skuInfoEntity.getSkuId());
//               skuReductionTo.getFullCount(skuInfoEntity.getPrice());
               //TODO 满减信息小于0就不做保存 fullcount fullprice
             R r1 =  couponFeignService.saveSkuReductionTo(skuReductionTo);
             Integer codeCouPonFeign = (Integer) r1.getOrDefault("code",null);
             if (codeCouPonFeign == null || HttpStatus.SC_INTERNAL_SERVER_ERROR ==Integer.valueOf(codeCouPonFeign)) {
                new RuntimeException("保存失败");
             }
           });
    }



}
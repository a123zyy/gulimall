package com.zyy.gulimall.coupon.service.impl;

import com.zyy.gulimall.common.to.MemberPrice;
import com.zyy.gulimall.common.to.SkuReductionTo;
import com.zyy.gulimall.coupon.entity.MemberPriceEntity;
import com.zyy.gulimall.coupon.entity.SkuLadderEntity;
import com.zyy.gulimall.coupon.service.MemberPriceService;
import com.zyy.gulimall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.coupon.dao.SkuFullReductionDao;
import com.zyy.gulimall.coupon.entity.SkuFullReductionEntity;
import com.zyy.gulimall.coupon.service.SkuFullReductionService;
import org.springframework.transaction.annotation.Transactional;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {
    @Autowired
    SkuLadderService skuLadderService;

    @Autowired
    SkuFullReductionService skuFullReductionService;
    @Autowired
    MemberPriceService memberPriceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    //不同服务信息异步
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSkuReductionTo(SkuReductionTo skuReductionTo) {
        //1.保存优惠、满减信息
        //(1、sms_sku_ladder
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(skuReductionTo.getSkuId());
        skuLadderEntity.setFullCount(skuReductionTo.getFullCount());
        skuLadderEntity.setDiscount(skuReductionTo.getDiscount());
        skuLadderEntity.setAddOther(skuReductionTo.getCountStatus());
        skuLadderService.save(skuLadderEntity);
        //(2、sms_sku_full_reduction
        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReductionTo,skuFullReductionEntity);
        skuFullReductionService.save(skuFullReductionEntity);

        //(3、sms_member_price
        List<MemberPrice> memberPrice = skuReductionTo.getMemberPrice();
        if (memberPrice!=null&& memberPrice.size()>0){
            List<MemberPriceEntity> collect = memberPrice.stream().map(member -> {
                MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
                memberPriceEntity.setMemberLevelId(member.getId());
                memberPriceEntity.setMemberLevelName(member.getName());
                memberPriceEntity.setMemberPrice(member.getPrice());
                memberPriceEntity.setAddOther(skuReductionTo.getCountStatus());
                memberPriceEntity.setSkuId(skuReductionTo.getSkuId());
                return memberPriceEntity;
            }).collect(Collectors.toList());
            memberPriceService.saveBatch(collect);
        }
    }

}
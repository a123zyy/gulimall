package com.zyy.gulimall.ware.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.ware.dao.PurchaseDetailDao;
import com.zyy.gulimall.ware.entity.PurchaseDetailEntity;
import com.zyy.gulimall.ware.service.PurchaseDetailService;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /**
         * status :0
         * wareId: 1
         * key purchase_id sku_id
         * */
        QueryWrapper<PurchaseDetailEntity> queryWrapper = new QueryWrapper<PurchaseDetailEntity>();
        String wareId = (String) params.get("wareId");
        if (StringUtils.isNotBlank(wareId)){
            queryWrapper.eq("ware_id",wareId);
        }

        String status = (String) params.get("status");
        if (StringUtils.isNotBlank(status)){
            queryWrapper.eq("status",status);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)){
            queryWrapper.and(w->{
                queryWrapper.eq("purchase_id",key).or().eq("sku_id",key);
            });
        }
        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),queryWrapper);

        return new PageUtils(page);
    }

    @Override
    public List<PurchaseDetailEntity> getListDetailByPurchaseIds(List<Long> ids) {
        List<PurchaseDetailEntity> purchaseDetailEntities = this.list(new QueryWrapper<PurchaseDetailEntity>().in("purchase_id",ids));
        return purchaseDetailEntities;
    }

}
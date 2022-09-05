package com.zyy.gulimall.ware.service.impl;

import com.zyy.gulimall.common.utils.WareConstant;
import com.zyy.gulimall.ware.entity.PurchaseDetailEntity;
import com.zyy.gulimall.ware.service.PurchaseDetailService;
import com.zyy.gulimall.ware.service.WareSkuService;
import com.zyy.gulimall.ware.vo.MergeVo;
import com.zyy.gulimall.ware.vo.PurchaseDoneVo;
import com.zyy.gulimall.ware.vo.PurchaseItemDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.ware.dao.PurchaseDao;
import com.zyy.gulimall.ware.entity.PurchaseEntity;
import com.zyy.gulimall.ware.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    WareSkuService wareSkuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceivePurchase(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>().eq("status","0").or().eq("status","1")
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void purchaseMerge(MergeVo mergeVo) {
        //TODO 采购单状态是0或者1才可以合并
        //如果采购单id是空就新建一个采购单
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId == null){
            PurchaseEntity purchase = new PurchaseEntity();
            purchase.setStatus(WareConstant.PurchaseEnum.CREATED.getCode());
            purchase.setCreateTime(new Date());
            purchase.setUpdateTime(new Date());
            this.save(purchase);
            purchaseId = purchase.getId();
        }
        List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetailEntity> purchaseDetailEntities =  items.stream().map(i->{
         PurchaseDetailEntity purchaseDetail = new PurchaseDetailEntity();
         purchaseDetail.setId(i);
         purchaseDetail.setPurchaseId(finalPurchaseId);
         purchaseDetail.setStatus(WareConstant.PurchaseDetailEnum.ASSIGNED.getCode());
         return purchaseDetail;
        }).collect(Collectors.toList());
        purchaseDetailService.updateBatchById(purchaseDetailEntities);
    }

    @Override
    @Transactional
    public void received(List<Long> ids) {
        List<PurchaseEntity> purchaseEntities = this.list(new QueryWrapper<PurchaseEntity>().in("id",ids).eq("status",0).or().eq("status",1));
        List<PurchaseEntity> collect = purchaseEntities.stream().map(item->{
            item.setStatus(WareConstant.PurchaseEnum.RECEIVE.getCode());
            return item;
        }).collect(Collectors.toList());
        List<Long> purchaseIds = collect.stream().map(PurchaseEntity::getId).collect(Collectors.toList());
        // 改变采购单的状态
        this.updateBatchById(collect);
        // 改变采购项的状态
        List<PurchaseDetailEntity> entities = purchaseDetailService.getListDetailByPurchaseIds(purchaseIds);
        List<PurchaseDetailEntity> detailEntities = entities.stream().map(entity->{
            PurchaseDetailEntity entity1 = new PurchaseDetailEntity();
            entity1.setId(entity.getId());
            entity1.setStatus(WareConstant.PurchaseDetailEnum.BUYING.getCode());
            return entity1;
        }).collect(Collectors.toList());
        purchaseDetailService.updateBatchById(detailEntities);
    }

    @Override
    @Transactional
    public void done(PurchaseDoneVo purchaseDoneVo) {
        // 1.改变采购单状态
        Long id = purchaseDoneVo.getId();

        // 2.改变采购项的状态
        Boolean flag = true;
        List<PurchaseItemDoneVo> itemDoneVos = purchaseDoneVo.getDoneVoList();
        List<PurchaseDetailEntity> updatas = new ArrayList<>();
        //如果其中有一个采购失败就失败
        for (PurchaseItemDoneVo item : itemDoneVos) {
            if (item.getStatus() == WareConstant.PurchaseDetailEnum.BUYERROR.getCode()){
                flag = false;
            }
            PurchaseDetailEntity purchaseDetail = new PurchaseDetailEntity();
            purchaseDetail.setStatus(item.getStatus());
            purchaseDetail.setId(item.getItemId());
            updatas.add(purchaseDetail);
        }
        List<Long> itemIds = itemDoneVos.stream()
                .filter(item->item.getStatus()!=WareConstant.PurchaseDetailEnum.BUYERROR.getCode())
                .map(PurchaseItemDoneVo::getItemId).collect(Collectors.toList());
        List<PurchaseDetailEntity> purchaseDetail = purchaseDetailService.listByIds(itemIds);
        wareSkuService.addStocks(purchaseDetail);
        purchaseDetailService.updateBatchById(updatas);
        // 1.改变采购单状态
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(id);
        purchase.setStatus(flag?WareConstant.PurchaseEnum.FINISH.getCode() : WareConstant.PurchaseEnum.HASERROR.getCode());
        this.updateById(purchase);
        // 3.将成功采购的进行入库
    }

}
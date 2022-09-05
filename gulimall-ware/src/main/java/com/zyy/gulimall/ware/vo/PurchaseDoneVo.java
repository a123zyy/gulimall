package com.zyy.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseDoneVo {
    public Long id;
    public List<PurchaseItemDoneVo> doneVoList;
}

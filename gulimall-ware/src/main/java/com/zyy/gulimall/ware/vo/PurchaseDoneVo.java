package com.zyy.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseDoneVo {
    public Long id;
    public List<PurchaseItemDoneVo> doneVoList;

    public PurchaseDoneVo(Long id, List<PurchaseItemDoneVo> doneVoList) {
        this.id = id;
        this.doneVoList = doneVoList;
    }

    public PurchaseDoneVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PurchaseItemDoneVo> getDoneVoList() {
        return doneVoList;
    }

    public void setDoneVoList(List<PurchaseItemDoneVo> doneVoList) {
        this.doneVoList = doneVoList;
    }
}

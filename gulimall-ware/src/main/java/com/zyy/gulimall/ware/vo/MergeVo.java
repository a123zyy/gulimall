package com.zyy.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

@Data
public class MergeVo {
    //items is purchaseDetail id
    private List<Long> items;
    //purchaseId 需求单id
    private Long purchaseId;

    public MergeVo() {
    }

    public MergeVo(List<Long> items, Long purchaseId) {
        this.items = items;
        this.purchaseId = purchaseId;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
}

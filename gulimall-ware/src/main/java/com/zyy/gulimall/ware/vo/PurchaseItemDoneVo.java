package com.zyy.gulimall.ware.vo;

import lombok.Data;

@Data
public class PurchaseItemDoneVo {
    public Long itemId;
    public int status;
    public String reason;

    public PurchaseItemDoneVo() {
    }

    public PurchaseItemDoneVo(Long itemId, int status, String reason) {
        this.itemId = itemId;
        this.status = status;
        this.reason = reason;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

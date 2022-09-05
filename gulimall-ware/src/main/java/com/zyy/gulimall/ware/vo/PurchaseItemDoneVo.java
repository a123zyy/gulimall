package com.zyy.gulimall.ware.vo;

import lombok.Data;

@Data
public class PurchaseItemDoneVo {
    public Long itemId;
    public int status;
    public String reason;
}

package com.zyy.gulimall.ware.vo;

import lombok.Data;

import java.util.List;

@Data
public class MergeVo {
    //items is purchaseDetail id
    private List<Long> items;
    //purchaseId 需求单id
    private Long purchaseId;
}

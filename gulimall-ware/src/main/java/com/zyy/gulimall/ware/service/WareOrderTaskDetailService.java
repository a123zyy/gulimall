package com.zyy.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:50:41
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


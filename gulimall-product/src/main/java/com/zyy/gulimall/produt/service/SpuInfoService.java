package com.zyy.gulimall.produt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.produt.entity.SpuInfoEntity;
import com.zyy.gulimall.produt.vo.SpuSaveVO;

import java.util.Map;

/**
 * spu信息
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-19 14:40:50
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVO spuInfo);

    PageUtils querySpuInfoPage(Map<String, Object> params);
}


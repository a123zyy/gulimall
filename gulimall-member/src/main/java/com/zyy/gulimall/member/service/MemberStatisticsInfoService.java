package com.zyy.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.member.entity.MemberStatisticsInfoEntity;

import java.util.Map;

/**
 * 会员统计信息
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:31:43
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.zyy.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.member.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author zyy
 * @email 451258633@qq.com
 * @date 2021-06-20 11:31:43
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


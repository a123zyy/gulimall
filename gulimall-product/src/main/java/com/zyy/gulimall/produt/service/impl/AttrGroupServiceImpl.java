package com.zyy.gulimall.produt.service.impl;

import com.zyy.gulimall.common.utils.Query;
import com.zyy.gulimall.produt.entity.AttrAttrgroupRelationEntity;
import com.zyy.gulimall.produt.entity.AttrEntity;
import com.zyy.gulimall.produt.service.AttrAttrgroupRelationService;
import com.zyy.gulimall.produt.service.AttrService;
import com.zyy.gulimall.produt.vo.AttrGroupRespVO;
import com.zyy.gulimall.produt.vo.AttrGroupVO;
import com.zyy.gulimall.produt.vo.AttrVO;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jcajce.provider.symmetric.TEA;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;

import com.zyy.gulimall.produt.dao.AttrGroupDao;
import com.zyy.gulimall.produt.entity.AttrGroupEntity;
import org.springframework.transaction.annotation.Transactional;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements com.zyy.gulimall.produt.service.AttrGroupService {

    @Autowired
    AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCategoryId(Map<String, Object> params, Long cateLogId) {
        //模糊查询
        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if (!StringUtils.isEmpty(key)){
            wrapper.and((obj)->{
                obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }
        if (cateLogId == 0){
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
            return new PageUtils(page);
        } else {
            wrapper.eq("catelog_id",cateLogId);
            //select * from attr_group where catelog_id and (id =key or name like %属性%)
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }

    }

    /**
     * 根据属性分组id 查询关联到的属性id attrId
     * @param attrgroupId
     * */
    @Override
    public List<AttrEntity> selectAttrIdsByAttrgroupId(Long attrgroupId) {
       List<AttrAttrgroupRelationEntity> relationEntityList = this.attrAttrgroupRelationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id",attrgroupId));
       if (relationEntityList.size() == 0){
           return new ArrayList<AttrEntity>();
       }
       List<Long> longs = relationEntityList.stream().map((item)->{
           return item.getAttrId();
       }).collect(Collectors.toList());
       return attrService.listByIds(longs);
    }

    @Override
    public PageUtils selectAttrInfosNotInGroupId(Map<String, Object> params,Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> relationEntityList = attrAttrgroupRelationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        if (relationEntityList.size() != 0){
            List<Long> collect = relationEntityList.stream().map(item -> {
                return item.getAttrId();
            }).collect(Collectors.toList());
            return attrService.queryByParamer(params, collect);
        } else {
            return attrService.queryPage(params);
        }

    }


    /**
     * 新增前查询是否重复提交
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveAttrGroup(AttrGroupEntity attrGroup) {
        List<AttrGroupEntity> list = list(new QueryWrapper<AttrGroupEntity>()
                .eq("attr_group_name", attrGroup.getAttrGroupName())
                .eq("sort", attrGroup.getSort())
                .eq("descript", attrGroup.getDescript()));
        if (list.size() == 0){
            this.saveOrUpdate(attrGroup);
        }
    }

    @Override
    public List<AttrGroupRespVO> selectByCatelogId(Long catelogId) {
        List<AttrGroupRespVO> catelog_id = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId)).stream().map(item -> {
            AttrGroupRespVO attrGroupRespVO = new AttrGroupRespVO();
            BeanUtils.copyProperties(item,attrGroupRespVO);

            List<AttrEntity> attrEntityList = selectAttrIdsByAttrgroupId(attrGroupRespVO.getAttrGroupId());
            if (attrEntityList.size()>0){
                List<AttrVO> collect = attrEntityList.stream().map(attrEntity -> {
                    AttrVO attrVO = new AttrVO();
                    BeanUtils.copyProperties(attrEntity, attrVO);
                    attrVO.setAttrGroupId(attrGroupRespVO.getAttrGroupId());
                    return attrVO;
                }).collect(Collectors.toList());
                attrGroupRespVO.setAttrs(collect);
            }
            return attrGroupRespVO;
        }).collect(Collectors.toList());
        return catelog_id;
    }


}
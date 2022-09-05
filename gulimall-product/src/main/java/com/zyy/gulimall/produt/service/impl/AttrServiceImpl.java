package com.zyy.gulimall.produt.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zyy.gulimall.common.consted.AttrEnum;
import com.zyy.gulimall.produt.entity.*;
import com.zyy.gulimall.produt.service.AttrAttrgroupRelationService;
import com.zyy.gulimall.produt.service.AttrGroupService;
import com.zyy.gulimall.produt.service.CategoryService;
import com.zyy.gulimall.produt.vo.AttrGroupVO;
import com.zyy.gulimall.produt.vo.AttrRespVO;
import com.zyy.gulimall.produt.vo.AttrVO;
import com.zyy.gulimall.produt.vo.ProductAttrValueVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyy.gulimall.common.utils.PageUtils;
import com.zyy.gulimall.common.utils.Query;

import com.zyy.gulimall.produt.dao.AttrDao;
import com.zyy.gulimall.produt.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    AttrGroupService attrGroupService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttr(AttrVO attr) {
        /**
         * 先查询看是否有重复提交
         * 1.保存attr
         * 2.保存关联表 attr group
         * */
        List<AttrEntity> list = list(new QueryWrapper<AttrEntity>().eq("attr_name", attr.getAttrName()).eq("value_select", attr.getValueSelect()).eq("attr_type", attr.getAttrType()));
        if (list.size() ==0){
            AttrEntity attrEntity = new AttrEntity();
            BeanUtils.copyProperties(attr,attrEntity);
            this.baseMapper.insert(attrEntity);
            if (attr.getAttrType() == AttrEnum.BASE_TYPE.getCode() && null != attr.getAttrGroupId()){
                AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
                attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
                attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
                attrAttrgroupRelationService.save(attrAttrgroupRelationEntity);
            }
        } else {
            throw new RuntimeException("重复提交！");
        }

    }

    /**
     * attr_id(规则)和attr_group是多对多的关系
     *
     *
     * */
    @Override
    public PageUtils queryPageByCategoryId(Map<String, Object> params,String type, Long categoryId) {
        QueryWrapper<AttrEntity> attrEntityQueryWrapper = new QueryWrapper<AttrEntity>();
        //base 基本类型
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)){
            attrEntityQueryWrapper.eq("attr_id",key).or().like("attr_name",key);
        }
        if (categoryId!=0){
            attrEntityQueryWrapper.eq("catelog_id",categoryId);
        }
        if (StringUtils.isEmpty(AttrEnum.getCodeByKey(type))){
            throw  new RuntimeException("type不能为空");
        }
        attrEntityQueryWrapper.eq("attr_type", AttrEnum.getCodeByKey(type));
       List<AttrEntity> entities= this.list(attrEntityQueryWrapper);
        System.out.println(entities);
        //返回的page
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                attrEntityQueryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrRespVO> attrRespVoList =  page.getRecords().stream().map(attr->{
            AttrRespVO attrRespVo = new AttrRespVO();
             BeanUtils.copyProperties(attr,attrRespVo);
             AttrAttrgroupRelationEntity relationEntity =  attrAttrgroupRelationService.getOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attr.getAttrId()));
            if (null != relationEntity){
              AttrGroupEntity group =  attrGroupService.getById(relationEntity.getAttrGroupId());
              if (null != group){
                  attrRespVo.setGroupName(group.getAttrGroupName());
              }
            }
            CategoryEntity categoryEntity =  categoryService.getById(attr.getCatelogId());
            if (null != categoryEntity){
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
             return attrRespVo;
         }).collect(Collectors.toList());
        pageUtils.setList(attrRespVoList);
        return pageUtils;
    }

    @Override
    public AttrVO getAttrEntityById(Long attrId) {
      AttrEntity attr =  this.baseMapper.selectById(attrId);
      AttrVO attrVO = new AttrVO();
      BeanUtils.copyProperties(attr,attrVO);
      attrVO.setCatelogPath(categoryService.getCatelogParentById(attr.getCatelogId()));
      AttrAttrgroupRelationEntity relationEntity =  attrAttrgroupRelationService.getOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attr.getAttrId()));
      if (null != relationEntity){
          //获取分组信息
          AttrGroupEntity attrGroup = attrGroupService.getById(relationEntity.getAttrGroupId());
          if (null != attrGroup){
              attrVO.setAttrGroupId(attrGroup.getAttrGroupId());
          }

      }
      return attrVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAttrEntityById(AttrVO attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        this.baseMapper.updateById(attrEntity);
        //TODO 修改后对应的分组也需要修改 基本类型需要保存分组
        if (AttrEnum.BASE_TYPE.getCode() == attr.getAttrType()){
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            if (null != attr.getAttrGroupId()){
                if (attrAttrgroupRelationService.count(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attr.getAttrId())) > 0){
                    attrAttrgroupRelationService.update(relationEntity, new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attr.getAttrId()));

                } else {
                    attrAttrgroupRelationService.save(relationEntity);
                }
            }
        }

    }

    @Override
    public PageUtils queryByParamer(Map<String, Object> params, List<Long> ids) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>().notIn("attr_id",ids)
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAttrIds(List<Long> ids) {
        //查看关联关系
        List<AttrAttrgroupRelationEntity> relationEntityList = attrAttrgroupRelationService.list(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_id",ids));
        if (CollectionUtils.isEmpty(relationEntityList)){
            this.removeByIds(ids);
            return true;
        }
        List<Long> longList = relationEntityList.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        ids.removeAll(longList);
        this.removeByIds(ids);
        return false;
    }

//    @Override
//    public void updateAttrValueBySpuId(List<ProductAttrValueVO> attrValueVOS, Long spuId) {
//        this.list(new QueryWrapper<ProductAttrValueEntity>())
//
//    }

}
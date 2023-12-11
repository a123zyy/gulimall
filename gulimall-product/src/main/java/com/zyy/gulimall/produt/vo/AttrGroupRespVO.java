package com.zyy.gulimall.produt.vo;

import com.zyy.gulimall.produt.entity.AttrGroupEntity;
import javafx.scene.effect.Light;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupRespVO extends AttrGroupEntity {
    public List<AttrVO> attrs;

    public List<AttrVO> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrVO> attrs) {
        this.attrs = attrs;
    }
}

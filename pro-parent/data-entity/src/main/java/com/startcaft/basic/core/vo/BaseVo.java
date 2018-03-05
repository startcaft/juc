package com.startcaft.basic.core.vo;

import com.startcaft.basic.core.entity.BaseEntity;

/**
 * @author startcaft
 * 数据Vo类，直接响应至客户端，是可以被序列化的
 * Created by startcaft on 2018/3/1.
 */
public abstract class BaseVo implements java.io.Serializable {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

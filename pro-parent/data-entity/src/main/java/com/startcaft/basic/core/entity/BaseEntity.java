package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.AbstractBean;
import com.startcaft.basic.core.vo.BaseVo;

/**
 * @author startcaft
 * 实体基类，包含一个Long类型的id属性，表示一个主键字段，并包含一个拷贝属性的模版方法
 */
public abstract class BaseEntity<T extends BaseVo> extends AbstractBean<T> {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 默认实现，如果没有其他属性拷贝，则使用父类的默认事件，如果有则可以自己重写该方法进行实现
     * @param t 目标对象
     */
    @Override
    protected void copyOtherProperties(T t) {

    }
}

package com.startcaft.basic.core.entity;

/**
 * @author startcaft
 * 实体基类，包含一个Long类型的id属性，表示一个主键字段
 */
public abstract class BaseEntity {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.startcaft.basic.core.vo;

import com.startcaft.basic.core.AbstractBean;
import com.startcaft.basic.core.entity.BaseEntity;

/**
 * @author startcaft
 * 数据Vo类，直接响应至客户端，是可以被序列化的
 * Created by startcaft on 2018/3/1.
 */
public abstract class BaseVo<T extends BaseEntity> extends AbstractBean<T> implements java.io.Serializable {

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

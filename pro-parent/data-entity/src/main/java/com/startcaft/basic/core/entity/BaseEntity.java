package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.vo.BaseVo;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.InvocationTargetException;

/**
 * @author startcaft
 * 实体基类，包含一个Long类型的id属性，表示一个主键字段，并包含一个拷贝属性的模版方法
 */
public abstract class BaseEntity<T extends BaseVo> {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 实体对象Entity 与 值对象Vo 之间属性拷贝的模版方法
     * @param t Vo对象
     * @return t
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public final T copyPropertiesTemplate(T t) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (t == null){
            throw new  NullPointerException("target Vo object is null");
        }

        // 执行 相同属性的 拷贝
        PropertyUtils.copyProperties(t,this);

        if (otherPropertiesHook()){
            copyOtherProperties(t);
        }

        return t;
    }

    /**
     * 拷贝 其他属性的抽象方法，具体的子类根据自己的实际情况实现
     * @param t Vo对象
     */
    protected abstract void copyOtherProperties(T t);

    /**
     * 拷贝其他属性的口子函数，默认为true，如果为false 则不会执行 copyOtherProperties() 方法；
     * @return true/false
     */
    protected boolean otherPropertiesHook(){
        return  true;
    }
}

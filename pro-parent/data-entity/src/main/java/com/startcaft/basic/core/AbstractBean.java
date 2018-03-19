/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/16 15:31
 * Description: bean的抽象类
 */
package com.startcaft.basic.core;

import com.startcaft.basic.core.exceptions.BasicProException;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈实体bean的抽象类〉
 *
 * @author StartCaft
 * @create 2018/3/16
 * @since 1.0.0
 */
public abstract class AbstractBean<T> implements Bean<T> {

    /**
     * 将调用者的所有属性和属性值进行一次
     * @param t 目标类型对象
     * @return
     */
    @Override
    public final T copyPropertiesTemplate(T t) {
        if (t == null){
            throw new BasicProException(new  NullPointerException("target object is null"));
        }
        try {
            PropertyUtils.copyProperties(t,this);
        } catch (IllegalAccessException e) {
            throw new BasicProException(e);
        } catch (InvocationTargetException e) {
            throw new BasicProException(e);
        } catch (NoSuchMethodException e) {
            throw new BasicProException(e);
        }

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

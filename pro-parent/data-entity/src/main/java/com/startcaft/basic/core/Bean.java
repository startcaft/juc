/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/16 15:27
 * Description: 实体/VO bean拥有的拷贝能力
 */
package com.startcaft.basic.core;

/**
 * 〈一句话功能简述〉<br> 
 * 〈实体/VO bean拥有的拷贝能力〉
 *
 * @author StartCaft
 * @create 2018/3/16
 * @since 1.0.0
 */
public interface Bean<T> {


    /**
     * 对象属性的拷贝
     * @param t 目标类型对象
     * @return 目标类型对象 T
     */
    T copyPropertiesTemplate(T t);
}

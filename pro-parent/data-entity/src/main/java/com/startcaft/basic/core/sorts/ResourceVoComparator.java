/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/15 17:15
 * Description: ResourceVo集合排序类
 */
package com.startcaft.basic.core.sorts;

import com.startcaft.basic.core.vo.ResourceVo;

import java.util.Comparator;

/**
 * 〈一句话功能简述〉<br> 
 * 〈ResourceVo集合排序类〉
 *
 * @author StartCaft
 * @create 2018/3/15
 * @since 1.0.0
 */
public class ResourceVoComparator implements Comparator<ResourceVo> {

    @Override
    public int compare(ResourceVo o1, ResourceVo o2) {
        return o1.getId().compareTo(o2.getId());
    }
}

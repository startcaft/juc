/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/16 16:35
 * Description: Easyui分页表格数据结构
 */
package com.startcaft.basic.core.vo;

import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Easyui分页表格数据结构〉
 *
 * @author StartCaft
 * @create 2018/4/16
 * @since 1.0.0
 */
public class EasyuiGrid<T> {

    private long total;

    private Set<T> rows;


    public EasyuiGrid(long total, Set<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}

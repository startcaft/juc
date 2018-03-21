/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/21 10:40
 * Description: easyui表格需要的数据结构
 */
package com.startcaft.basic.core.beans;

import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈easyui表格需要的数据结构〉
 *
 * @author StartCaft
 * @create 2018/3/21
 * @since 1.0.0
 */
public class DataGridBean<T> {

    private int total;
    private Set<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Set<T> getRows() {
        return rows;
    }

    public void setRows(Set<T> rows) {
        this.rows = rows;
    }

    public DataGridBean(int total, Set<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}

/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/16 16:35
 * Description: Easyui分页表格数据结构
 */
package com.startcaft.basic.core.vo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Easyui分页表格数据结构〉
 *
 * Set 在反序列化时候，特别是TreeSet，在调用 add 方法时候会无法进行比较两个对象（找不到比较器），所以这里改成List
 *
 * @author StartCaft
 * @create 2018/4/16
 * @since 1.0.0
 */
public class EasyuiGrid<T> implements java.io.Serializable {

    private long total;

    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public EasyuiGrid(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyuiGrid() {
    }
}

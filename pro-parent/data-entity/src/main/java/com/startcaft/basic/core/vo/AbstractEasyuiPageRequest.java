/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/16 16:42
 * Description: easyui表格分页时提交的数据
 */
package com.startcaft.basic.core.vo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈easyui表格分页时提交的数据〉
 *
 * @author StartCaft
 * @create 2018/4/16
 * @since 1.0.0
 */
public abstract class AbstractEasyuiPageRequest {

    /**
     * 当前页数
     */
    protected int page;

    /**
     * 每页多少数据
     */
    protected int rows;

    /**
     * 排序字段
     */
    protected String sort;

    /**
     * 排序方向
     */
    protected String order;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

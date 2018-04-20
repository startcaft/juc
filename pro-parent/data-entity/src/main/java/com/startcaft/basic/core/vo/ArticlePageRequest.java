/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 16:46
 * Description: Article分页查询对象
 */
package com.startcaft.basic.core.vo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Article分页查询对象〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
public class ArticlePageRequest extends AbstractEasyuiPageRequest {

    /**
     * 根据标题查询
     */
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 16:03
 * Description: 文章管理
 */
package com.startcaft.basic.service;

import com.startcaft.basic.core.beans.ArticleBean;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.ArticlePageRequest;
import com.startcaft.basic.core.vo.ArticleVo;
import com.startcaft.basic.core.vo.EasyuiGrid;

/**
 * 〈一句话功能简述〉<br> 
 * 〈文章管理〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
public interface IArticleService {

    /**
     * 新增文章
     * @param bean
     * @throws BasicProException
     */
    void saveArticle(ArticleBean bean) throws BasicProException;

    /**
     * 分页查询
     * @param request
     * @return
     * @throws BasicProException
     */
    EasyuiGrid<ArticleVo> pageSearch(ArticlePageRequest request) throws BasicProException;
}

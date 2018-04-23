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

import java.util.Set;

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

    /**
     * 获取文章详细，先从 redis 中获取，如果没有才到数据库中去查询，查询到了之后保存到redis中
     * @param id
     * @return
     * @throws BasicProException
     */
    ArticleVo getDetail(long id) throws BasicProException;

    /**
     * 获取刺激战场分类下的8条最新的文章
     * @return
     * @throws BasicProException
     */
    Set<ArticleVo> getGameTop8() throws BasicProException;
}

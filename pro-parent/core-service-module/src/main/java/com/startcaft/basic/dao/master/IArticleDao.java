package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.Article;

import java.util.Map;
import java.util.Set;

public interface IArticleDao {

    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Article record);

    /**
     * 查询详细
     * @param id
     * @return
     */
    Article selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * 动态查询
     * @param params
     * @return
     */
    Set<Article> selectListDynamic(Map<String,Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
    Set<Article> selectUserPage(Map<String,Object> params);

    /**
     * 查询指定分类下的最新8条文章
     * @param dicItemId
     * @return
     */
    Set<Article> selectTopByDic(long dicItemId);
}
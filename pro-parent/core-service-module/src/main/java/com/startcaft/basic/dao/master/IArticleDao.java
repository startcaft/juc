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

    Article selectByPrimaryKey(Long id);

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
}
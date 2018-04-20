/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 16:05
 * Description: 文章管理实现类
 */
package com.startcaft.basic.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.startcaft.basic.core.beans.ArticleBean;
import com.startcaft.basic.core.entity.Article;
import com.startcaft.basic.core.entity.User;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.SqlExecuteException;
import com.startcaft.basic.core.exceptions.UniqueException;
import com.startcaft.basic.core.vo.ArticlePageRequest;
import com.startcaft.basic.core.vo.ArticleVo;
import com.startcaft.basic.core.vo.EasyuiGrid;
import com.startcaft.basic.core.vo.UserVo;
import com.startcaft.basic.dao.master.IArticleDao;
import com.startcaft.basic.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈文章管理实现类〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void saveArticle(ArticleBean bean) throws BasicProException {
        {
            // 确认标题不能重复
            Map<String,Object> params = new HashMap<>(1);
            params.put("title",bean.getArticleTitle());
            Set<Article> set = articleDao.selectListDynamic(params);
            if (set != null && set.size() > 0) {
                throw new UniqueException("文章标题:[" + bean.getArticleTitle() + "]，已经存在");
            }

            int result = articleDao.insert(bean.copyPropertiesTemplate(new Article()));
            if (result != 1){
                throw new SqlExecuteException("execute insert result is error");
            }
        }
    }

    @Override
    public EasyuiGrid<ArticleVo> pageSearch(ArticlePageRequest request) throws BasicProException {
        {
            Map<String,Object> params = new HashMap<>();
            if (!StringUtils.isEmpty(request.getTitle())){
                params.put("title",request.getTitle());
            }

            Page<Object> page = PageHelper.startPage(request.getPage(),request.getRows());
            Set<Article> articleSet = articleDao.selectUserPage(params);

            Set<ArticleVo> voSet = new TreeSet<>(new Comparator<ArticleVo>() {
                @Override
                public int compare(ArticleVo o1, ArticleVo o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });

            if (articleSet != null && articleSet.size() > 0){
                articleSet.forEach((entity) -> {
                    voSet.add(entity.copyPropertiesTemplate(new ArticleVo()));
                });
            }

            EasyuiGrid<ArticleVo> grid = new EasyuiGrid<>(page.getTotal(),voSet);
            return grid;
        }
    }
}

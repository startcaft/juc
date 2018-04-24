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
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.SqlExecuteException;
import com.startcaft.basic.core.exceptions.UniqueException;
import com.startcaft.basic.core.vo.ArticlePageRequest;
import com.startcaft.basic.core.vo.ArticleVo;
import com.startcaft.basic.core.vo.EasyuiGrid;
import com.startcaft.basic.dao.master.IArticleDao;
import com.startcaft.basic.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 〈一句话功能简述〉<br> 
 * 〈文章管理实现类〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
@Service
public class ArticleServiceImpl implements IArticleService,java.io.Serializable {

    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private RedisTemplate<String,ArticleVo> redisTemplate;

    @Autowired
    private RedisTemplate<String,EasyuiGrid<ArticleVo>> pageRedisTemplate;

    /**
     * 文章缓存的key前缀
     */
    private static final String ARTICLE_KEY_PREFIX = "article:";

    /**
     * 文章缓存的过期时间，24小时
     */
    private static final long ARTICLE_TIME_OUT = 24;

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
            ValueOperations<String,EasyuiGrid<ArticleVo>> operations = pageRedisTemplate.opsForValue();

            StringBuilder keyBuilder = new StringBuilder(ARTICLE_KEY_PREFIX);
            keyBuilder.append("page:").append("p:" + request.getPage()).append(":r:" + request.getRows());

            Map<String,Object> params = new HashMap<>();
            if (!StringUtils.isEmpty(request.getTitle())){
                params.put("title",request.getTitle());
                keyBuilder.append(":t:" + request.getTitle());
            }
            if (request.getDicItemId() != null && request.getDicItemId().intValue() > 0){
                params.put("dicItemId",request.getDicItemId());
                keyBuilder.append(":d:" + request.getTitle());
            }

            EasyuiGrid<ArticleVo> cacheGrid = operations.get(keyBuilder.toString());
            Optional<EasyuiGrid<ArticleVo>> optionalGrid = Optional.ofNullable(cacheGrid);

            cacheGrid = optionalGrid.orElseGet(() -> {
                // 必须放在分页 dao 调用的前面
                Page<Object> page = PageHelper.startPage(request.getPage(),request.getRows());
                List<Article> articles = articleDao.selectUserPage(params);
                List<ArticleVo> voList = new ArrayList<>(articles.size());

                if (articles != null && articles.size() > 0){
                    articles.forEach((entity) -> {
                        voList.add(entity.copyPropertiesTemplate(new ArticleVo()));
                    });
                }

                EasyuiGrid<ArticleVo> grid = new EasyuiGrid<>(page.getTotal(),voList);

                return grid;
            });

            operations.set(keyBuilder.toString(),cacheGrid,1, TimeUnit.HOURS);

            return cacheGrid;
        }
    }

    @Override
    public ArticleVo getDetail(long id) throws BasicProException {
        {
            ValueOperations<String,ArticleVo> operations = redisTemplate.opsForValue();
            // 查找缓存
            ArticleVo cacheVo = operations.get(ARTICLE_KEY_PREFIX + id);
            Optional<ArticleVo> optionalArticleVo = Optional.ofNullable(cacheVo);

            Supplier<ArticleVo> supplier = () -> {
                Article entity = articleDao.selectByPrimaryKey(id);
                if (entity == null){
                    throw new BasicProException("查询不到指定的Article数据");
                };
                ArticleVo vo = new ArticleVo();
                return entity.copyPropertiesTemplate(vo);
            };

            // 缓存中没有则从数据库中查询，注意查询数据库也许会查询不到，那就直接抛出异常
            cacheVo = optionalArticleVo.orElseGet(supplier);

            // 保存查询结果到 redis 中，
            operations.set(ARTICLE_KEY_PREFIX + id,cacheVo,ARTICLE_TIME_OUT, TimeUnit.HOURS);

            return cacheVo;
        }
    }

    @Override
    public Set<ArticleVo> getGameTop8() throws BasicProException {
        {
            SetOperations<String,ArticleVo> operations = redisTemplate.opsForSet();
            // 先查询缓存
            Set<ArticleVo> cacheVoSet = operations.members(ARTICLE_KEY_PREFIX + "game:top8");
            if (cacheVoSet == null || cacheVoSet.size() <= 0){
                // 查询数据库
                Set<Article> set = articleDao.selectTopByDic(12);
                Set<ArticleVo> tempSet = new TreeSet<>(new Comparator<ArticleVo>() {
                    @Override
                    public int compare(ArticleVo o1, ArticleVo o2) {
                        return o1.getCreateTime().compareTo(o2.getCreateTime());
                    };
                });
                set.forEach((entity) -> {
                    ArticleVo vo = new ArticleVo();
                    entity.copyPropertiesTemplate(vo);

                    tempSet.add(vo);
                });

                cacheVoSet = tempSet;
            }

            // 保存结果集到缓存
            ArticleVo[] articleArray = cacheVoSet.toArray(new ArticleVo[cacheVoSet.size()]);
            operations.add(ARTICLE_KEY_PREFIX + "game:top8",articleArray);
            redisTemplate.expire(ARTICLE_KEY_PREFIX + "game:top8",ARTICLE_TIME_OUT,TimeUnit.HOURS);

            return cacheVoSet;
        }
    }
}

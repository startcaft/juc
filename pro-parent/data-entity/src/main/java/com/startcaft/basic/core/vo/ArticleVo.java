/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 15:59
 * Description:
 */
package com.startcaft.basic.core.vo;

import com.startcaft.basic.core.entity.Article;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
public class ArticleVo extends BaseVo<Article> {

    private String articleTitle;

    private String articleContent;

    private Long dicItemId;

    private Date createTime;

    private String articleDesc;

    private String dicItemName;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Long getDicItemId() {
        return dicItemId;
    }

    public void setDicItemId(Long dicItemId) {
        this.dicItemId = dicItemId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getDicItemName() {
        return dicItemName;
    }

    public void setDicItemName(String dicItemName) {
        this.dicItemName = dicItemName;
    }
}

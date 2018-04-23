/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 15:59
 * Description:
 */
package com.startcaft.basic.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.startcaft.basic.core.entity.Article;

import java.util.Date;
import java.util.Objects;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        ArticleVo articleVo = (ArticleVo) o;
        return Objects.equals(articleTitle, articleVo.articleTitle) &&
                Objects.equals(articleContent, articleVo.articleContent) &&
                Objects.equals(dicItemId, articleVo.dicItemId) &&
                Objects.equals(createTime, articleVo.createTime) &&
                Objects.equals(articleDesc, articleVo.articleDesc) &&
                Objects.equals(dicItemName, articleVo.dicItemName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleTitle, articleContent, dicItemId, createTime, articleDesc, dicItemName);
    }
}

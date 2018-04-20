package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.vo.ArticleVo;

import java.util.Date;
import java.util.Objects;

/**
 * @author Administrator
 */
public class Article extends BaseEntity<ArticleVo> {

    private String articleTitle;

    private String articleContent;

    private Long dicItemId;

    private Date createTime;

    private String articleDesc;

    private DicItem dicItem;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
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
        this.articleDesc = articleDesc == null ? null : articleDesc.trim();
    }

    public DicItem getDicItem() {
        return dicItem;
    }

    public void setDicItem(DicItem dicItem) {
        this.dicItem = dicItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle) &&
                Objects.equals(articleContent, article.articleContent) &&
                Objects.equals(dicItemId, article.dicItemId) &&
                Objects.equals(createTime, article.createTime) &&
                Objects.equals(articleDesc, article.articleDesc) &&
                Objects.equals(dicItem, article.dicItem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleTitle, articleContent, dicItemId, createTime, articleDesc, dicItem);
    }

    @Override
    protected void copyOtherProperties(ArticleVo vo) {
        if (this.dicItem != null){
            vo.setDicItemName(this.dicItem.getName());
        }
    }

    @Override
    protected boolean otherPropertiesHook() {
        return true;
    }
}
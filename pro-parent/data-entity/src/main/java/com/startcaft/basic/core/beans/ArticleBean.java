/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 15:59
 * Description:
 */
package com.startcaft.basic.core.beans;

import com.startcaft.basic.core.entity.Article;
import com.startcaft.basic.core.vo.BaseVo;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
public class ArticleBean extends BaseVo<Article> {

    @NotBlank(message = "文章标题不能为空")
    @Length(message = "长度不能超过200个字")
    private String articleTitle;

    @NotBlank(message = "文章内容不能为空")
    private String articleContent;

    @NotNull(message = "必须选择一个文章类型")
    private Long dicItemId;

    private Date createTime;

    @NotBlank(message = "文章描述不能为空")
    private String articleDesc;

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
}

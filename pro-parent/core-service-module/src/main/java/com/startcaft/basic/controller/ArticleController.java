/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 16:21
 * Description: 文章服务
 */
package com.startcaft.basic.controller;

import com.startcaft.basic.core.beans.ArticleBean;
import com.startcaft.basic.core.vo.*;
import com.startcaft.basic.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 〈一句话功能简述〉<br> 
 * 〈文章服务〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
@Api(value = "/articles",description = "文章相关服务API")
@RestController
@RequestMapping("/articles")
public class ArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "添加文章",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bean",required = true,dataType = "ArticleBean",paramType = "body")
    })
    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean<String> addResource(@Valid @RequestBody ArticleBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            articleService.saveArticle(bean);
            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("save Article [" + bean.getArticleTitle() + "] success");

            return result;
        }
    }

    @ApiOperation(value = "文章分页查询",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "rows",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "orgId",required = false,dataType = "Long",paramType = "query"),
    })
    @GetMapping(value = "/page",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public EasyuiGrid<ArticleVo> getUserPage(@RequestParam(value = "page",required = true) int pageIndex,
                                             @RequestParam(value = "rows",required = true) int pageSize,
                                             @RequestParam(value = "title",required = false) String title){
        {
            ArticlePageRequest request = new ArticlePageRequest();
            request.setPage(pageIndex);
            request.setRows(pageSize);
            request.setTitle(title);
            return articleService.pageSearch(request);
        }
    }
}

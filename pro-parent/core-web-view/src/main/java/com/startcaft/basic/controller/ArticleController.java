/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/19 16:24
 * Description: 文章服务
 */
package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈文章服务〉
 *
 * @author StartCaft
 * @create 2018/4/19
 * @since 1.0.0
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @GetMapping("/list")
    public String dicIndex(){
        return "admin/article_index";
    }

    @GetMapping("/add")
    public String dicAdd(){
        return "admin/article_add";
    }

    @GetMapping("/edit/{id}")
    public String dicModify(@PathVariable(value = "id",required = true) Long id, Model model){
        model.addAttribute("articleId",id);
        return "admin/article_add";
    }
}

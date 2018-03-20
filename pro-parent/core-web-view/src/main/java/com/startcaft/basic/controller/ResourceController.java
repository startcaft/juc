/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/15 14:20
 * Description: 资源信息
 */
package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈资源信息〉
 *
 * @author StartCaft
 * @create 2018/3/15
 * @since 1.0.0
 */
@Controller
@RequestMapping("/admin/resource")
public class ResourceController {

    @GetMapping("/list")
    public String resoruceIndex(){
        return "admin/resource_index";
    }

    @GetMapping("/add")
    public String resoruceAdd(){
        return "admin/resource_add";
    }

    @GetMapping("/edit/{id}")
    public String resourceModify(@PathVariable(value = "id",required = true) Long id, Model model){
        model.addAttribute("resId",id);
        return "admin/resource_add";
    }
}

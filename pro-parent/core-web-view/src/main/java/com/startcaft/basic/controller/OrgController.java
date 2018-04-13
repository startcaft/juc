/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/13 15:51
 * Description: org视图
 */
package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈org视图〉
 *
 * @author StartCaft
 * @create 2018/4/13
 * @since 1.0.0
 */
@Controller
@RequestMapping("/admin/org")
public class OrgController {

    @GetMapping("/list")
    public String dicIndex(){
        return "admin/org_index";
    }

    @GetMapping("/add")
    public String dicAdd(){
        return "admin/org_add";
    }

    @GetMapping("/edit/{id}")
    public String dicModify(@PathVariable(value = "id",required = true) Long id, Model model){
        model.addAttribute("orgId",id);
        return "admin/org_add";
    }
}

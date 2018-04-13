/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/2 14:59
 * Description: 数据字典Controller
 */
package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据字典Controller〉
 *
 * @author StartCaft
 * @create 2018/4/2
 * @since 1.0.0
 */
@Controller
@RequestMapping("/admin/dic")
public class DicController {

    @GetMapping("/list")
    public String dicIndex(){
        return "admin/dic_index";
    }

    @GetMapping("/add")
    public String dicAdd(){
        return "admin/dic_add";
    }

    @GetMapping("/edit/{id}")
    public String dicModify(@PathVariable(value = "id",required = true) Long id, Model model){
        model.addAttribute("dicId",id);
        return "admin/dic_add";
    }
}

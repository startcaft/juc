/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/8 15:06
 * Description: 用户管理
 */
package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户管理〉
 *
 * @author StartCaft
 * @create 2018/3/8
 * @since 1.0.0
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {



    @GetMapping("/list")
    public String list(){
        return "admin/user_index";
    }

    @GetMapping("/add")
    public String add(){
        return "admin/user_add";
    }

    @GetMapping("/edit/{id}")
    public String roleModify(@PathVariable(value = "id",required = true) Long id, Model model){
        model.addAttribute("userId",id);
        return "admin/user_add";
    }

    @GetMapping("/pwd")
    public String UpdatePwd(){
        return "pwd";
    }
}

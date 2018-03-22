/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/21 10:06
 * Description: 角色相关试图
 */
package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈角色相关视图〉
 *
 * @author StartCaft
 * @create 2018/3/21
 * @since 1.0.0
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @GetMapping("/list")
    public String list(){
        return "admin/role_index";
    }

    @GetMapping("/grant/{roleId}")
    public String grant(@PathVariable(value = "roleId",required = true) long roleId, Model model){
        model.addAttribute("roleId",roleId);
        return "admin/role_grant";
    }
}

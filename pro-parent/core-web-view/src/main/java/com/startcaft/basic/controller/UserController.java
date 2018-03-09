/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/8 15:06
 * Description: 用户管理
 */
package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户管理〉
 *
 * @author StartCaft
 * @create 2018/3/8
 * @since 1.0.0
 */
@Controller
public class UserController {

    @GetMapping("/")
    public String Login(){
        return "login";
    }


    @GetMapping("/index")
    public String Index(){
        return "index";
    }
}

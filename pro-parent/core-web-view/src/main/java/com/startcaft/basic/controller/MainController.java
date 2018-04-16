/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/16 15:35
 * Description: 首页视图控制器
 */
package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈首页视图控制器〉
 *
 * @author StartCaft
 * @create 2018/4/16
 * @since 1.0.0
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String Login(){
        return "login";
    }

    @GetMapping("/index")
    public String Index(){
        return "index";
    }

    @GetMapping("/control")
    public String Control(){
        return "control";
    }
}

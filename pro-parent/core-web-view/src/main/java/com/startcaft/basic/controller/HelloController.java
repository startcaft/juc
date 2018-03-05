package com.startcaft.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author startcaft
 * @date 2018/3/2
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("hello","hello Thymeleaf!");
        return "login";
    }


    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }
}

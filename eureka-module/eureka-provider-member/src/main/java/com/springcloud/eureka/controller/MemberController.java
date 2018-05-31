/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/30 14:02
 * Description:
 */
package com.springcloud.eureka.controller;

import com.springcloud.eureka.ServiceInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/5/30
 * @since 1.0.0
 */
@RestController
public class MemberController {

    @Autowired
    private ServiceInfoUtil info;

    @GetMapping(value = "/members",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<String> getMembers(){
        ArrayList<String> lists = new ArrayList<>(3);
        lists.add("张三");
        lists.add("李四");
        lists.add("王五");
        lists.add(String.valueOf(info.getPort()));
        return lists;
    }
}

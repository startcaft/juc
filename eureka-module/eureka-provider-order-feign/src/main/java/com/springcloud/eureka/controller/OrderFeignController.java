/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/6/1 10:21
 * Description: Feign调用
 */
package com.springcloud.eureka.controller;

import com.springcloud.eureka.MemberFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Feign调用〉
 *
 * @author StartCaft
 * @create 2018/6/1
 * @since 1.0.0
 */
@RestController
public class OrderFeignController {

    protected Logger logger = LoggerFactory.getLogger(OrderFeignController.class);

    @Autowired
    private MemberFeign memberFeign;

    @GetMapping(value = "/orders",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<String> getOrdersEx(){
        return memberFeign.getOrderMembers();
    }
}

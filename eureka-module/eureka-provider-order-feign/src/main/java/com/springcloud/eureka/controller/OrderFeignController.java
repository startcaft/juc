/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/6/1 10:21
 * Description: Feign调用
 */
package com.springcloud.eureka.controller;

import com.springcloud.eureka.RefactorMemberFeign;
import member.service.api.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private RefactorMemberFeign memberFeign;

    @GetMapping(value = "/ordersEx",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String getFeignEx(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(memberFeign.getFeign("DIDI")).append("\n");
        stringBuilder.append(memberFeign.getFeign("DIDI",30)).append("\n");
        stringBuilder.append(memberFeign.getFeign(new User("DIDI",30))).append("\n");
        return stringBuilder.toString();
    }
}

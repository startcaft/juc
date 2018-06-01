/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/30 14:02
 * Description:
 */
package com.springcloud.eureka.controller;

import member.service.api.MemberService;
import member.service.api.User;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/5/30
 * @since 1.0.0
 */
@RestController
public class RefactorMemberController implements MemberService {

    @Override
    public String getFeign(String name) {
        return "Hello," + name;
    }

    @Override
    public User getFeign(String name, Integer age) {
        return new User(name,age);
    }

    @Override
    public String getFeign(User user) {
        return "Hello," + user.getName() + ", " + user.getAge();
    }

}

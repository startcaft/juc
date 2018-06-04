/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/30 14:02
 * Description:
 */
package com.springcloud.eureka.controller;

import com.springcloud.eureka.ServiceInfoUtil;
import member.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RefactorMemberController implements MemberService {

    @Autowired
    private ServiceInfoUtil infoUtil;

    @Override
    public List<String> getMemebers() {
        List<String> memeberList = new ArrayList<>();
        memeberList.add("张三");
        memeberList.add("李四");
        memeberList.add("王五");
        memeberList.add(String.valueOf(infoUtil.getPort()));
        return memeberList;
    }
}

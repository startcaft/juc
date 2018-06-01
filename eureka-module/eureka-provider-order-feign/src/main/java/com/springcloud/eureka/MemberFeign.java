package com.springcloud.eureka;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "service-member")//绑定服务
public interface MemberFeign {

    @GetMapping("/members")//绑定资源
    List<String> getOrderMembers();
}

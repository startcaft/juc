package com.springcloud.eureka;

import com.springcloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "service-member")//绑定服务
public interface MemberFeign {

    @GetMapping("/members")//绑定资源
    List<String> getOrderMembers();

    @GetMapping("/feign")
    String getFeign(@RequestParam(value = "name",required = true) String name);

    @GetMapping("/feign1")
    User getFeign(@RequestHeader(value = "name",required = true) String name,
                  @RequestHeader(value = "age",required = true) Integer age);

    @PostMapping("/feign2")
    String getFeign(@RequestBody User user);
}

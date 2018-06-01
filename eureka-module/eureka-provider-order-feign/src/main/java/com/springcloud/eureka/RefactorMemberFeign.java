package com.springcloud.eureka;

import member.service.api.MemberService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "service-member")//绑定服务
public interface RefactorMemberFeign extends MemberService {
}

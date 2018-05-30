package com.springcloud.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 〈一句话功能简述〉<br>
 * 〈会员服务提供者〉
 *
 * @author StartCaft
 * @create 2018/5/30
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class MemberApp {
    public static void main(String[] args) {
        SpringApplication.run(MemberApp.class,args);
    }
}

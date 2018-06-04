package com.springcloud.eureka;


import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
public class OrderFeignApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignApp.class,args);
    }

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}

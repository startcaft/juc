/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/30 11:34
 * Description: Eureka注册中心
 */
package com.startcaft.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Eureka注册中心〉
 *
 * @author StartCaft
 * @create 2018/5/30
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class ServerApp {

    public static void main(String[] args){
        SpringApplication.run(ServerApp.class,args);
    }
}

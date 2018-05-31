/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/30 14:55
 * Description:
 */
package com.springcloud.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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
public class OrderController {

    protected Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/orders",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<String> getOrders(){
        return restTemplate.getForObject("http://service-member/members",List.class);
    }

    @GetMapping(value = "/ordersEx",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<String> getOrdersEx(){
        ServiceInstance si = this.loadBalancerClient.choose("service-member");
        URI serviceUri = URI.create(String.format("http://%s:%s/members",si.getHost(),si.getPort()));
        logger.info("Target service uri = {}. ", serviceUri.toString());
        return new RestTemplate().getForObject(serviceUri, List.class);
    }
}

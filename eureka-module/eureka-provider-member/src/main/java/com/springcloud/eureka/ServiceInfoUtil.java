/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/31 9:28
 * Description: 程序启动监听器
 */
package com.springcloud.eureka;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈程序启动监听器〉
 *
 * @author StartCaft
 * @create 2018/5/31
 * @since 1.0.0
 */
@Component
public class ServiceInfoUtil implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    private int serverPort;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        this.serverPort = event.getEmbeddedServletContainer().getPort();
    }

    public int getPort() {
        return this.serverPort;
    }
}

/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/7 15:35
 * Description: EhCache缓存配置信息
 */
package com.startcaft.basic.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Shiro EhCache缓存配置信息〉
 *
 * @author StartCaft
 * @create 2018/3/7
 * @since 1.0.0
 */
@Configuration
public class CacheConfig {

    @Bean
    public EhCacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }
}

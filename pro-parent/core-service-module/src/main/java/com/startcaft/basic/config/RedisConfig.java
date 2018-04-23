/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/23 14:00
 * Description: Redis配置
 */
package com.startcaft.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Redis配置〉
 *
 * @author StartCaft
 * @create 2018/4/23
 * @since 1.0.0
 */
@Configuration
public class RedisConfig {

    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.usePool}")
    private boolean usePool;

    @Value("${redis.database}")
    private int database;

    @Value("${redis.maxActive}")
    private int maxActive;

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.maxWait}")
    private long maxWait;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.whenExhaustedAction}")
    private boolean whenExhaustedAction;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isUsePool() {
        return usePool;
    }

    public void setUsePool(boolean usePool) {
        this.usePool = usePool;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public boolean getWhenExhaustedAction() {
        return whenExhaustedAction;
    }

    public void setWhenExhaustedAction(boolean whenExhaustedAction) {
        this.whenExhaustedAction = whenExhaustedAction;
    }

    /**
     * Redis 连接池配置
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        config.setBlockWhenExhausted(whenExhaustedAction);
        return config;
    }

    /**
     * Redis 连接配置
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(@Autowired JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(jedisPoolConfig);
        factory.setHostName(hostName);
        factory.setPort(port);
        factory.setTimeout(timeout);
        factory.setUsePool(usePool);
        factory.setDatabase(database);
        return factory;
    }

    @Bean
    public RedisTemplate redisTemplate(@Autowired JedisConnectionFactory jedisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }
}

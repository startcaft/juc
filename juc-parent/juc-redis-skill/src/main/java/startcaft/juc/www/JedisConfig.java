package startcaft.juc.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: pikai
 * @Description: Jedis 客户端连接池配置
 * @Date: Created in 2018/2/19 23:21
 */
@Configuration
public class JedisConfig {

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxTotal(32);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(300000);
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(60000);
        jedisPoolConfig.setBlockWhenExhausted(true);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(@Autowired JedisPoolConfig jedisPoolConfig){
        JedisPool jedisPool = new JedisPool(
                jedisPoolConfig,
                "119.23.56.247",
                6379,
                1500,
                "5904395",
                3);
        return jedisPool;
    }
}

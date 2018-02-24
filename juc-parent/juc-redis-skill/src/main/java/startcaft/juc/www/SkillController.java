package startcaft.juc.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.security.RunAs;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/19 23:57
 */
@Configuration
@RestController
public class SkillController {

    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/skill")
    public String skill(){
        // 开启一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(20);

        Jedis jedis = this.jedisPool.getResource();
        // 设置其实的秒杀数
        jedis.set(MyRedisTest.WATCH_KEY,"100");

        // 模拟1000个人来发起抢购
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new SkillRunnable(jedis,"user_" + MyRedisTest.getRandomString(6));
            executor.execute(runnable);
        }
        executor.shutdown();

        return "ok";
    }
}

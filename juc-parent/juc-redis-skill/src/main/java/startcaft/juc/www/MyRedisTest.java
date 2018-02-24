package startcaft.juc.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

/**
 * @Author: pikai
 * @Description: 模拟redis高并发
 * @Date: Created in 2018/2/19 23:21
 */
@SpringBootApplication
public class MyRedisTest {

    // 要监视的keys
    public static final String WATCH_KEY  = "skillkeys";


    public static void main(String[] args){
        new SpringApplication(MyRedisTest.class).run(args);

    }


    /**
     * 生成随机字符串
     * @param length 随机字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}

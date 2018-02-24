package startcaft.juc.www;

import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @Author: pikai
 * @Description:模拟秒杀的线程
 * @Date: Created in 2018/2/19 23:33
 */
public class SkillRunnable implements Runnable {


    private Jedis jedis;
    private String userInfo;

    public SkillRunnable(Jedis jedis,String userInfo) {
        this.jedis = jedis;
        this.userInfo = userInfo;

    }

    @Override
    public void run() {
        try{
            this.jedis.watch(MyRedisTest.WATCH_KEY);

            String val = jedis.get(MyRedisTest.WATCH_KEY);
            int valInt = Integer.valueOf(val);

            if (valInt <= 100 && valInt >= 1) {
                // 开启事务
                Transaction tx = jedis.multi();
                tx.incr(MyRedisTest.WATCH_KEY);

                // 提交事务，如果此时 MyRedisTest.WATCH_KEY 被修改了，则返回 null。
                List<Object> list = tx.exec();

                if(list == null && list.size() == 0){
                    // 秒杀失败
                    String failUserInfo = "fail_" + this.userInfo;
                    String failMsg = "用户:" + failUserInfo + "，商品秒杀失败！";
                    System.out.println(failMsg);

                    /*抢购失败的业务逻辑*/
                    jedis.setnx(failUserInfo,failMsg);
                }
                else {
                    // 秒杀成功
                    for(Object succ : list){
                        String successUserInfo ="success_"+succ.toString() + this.userInfo;
                        String successMsg="用户：" + successUserInfo + "，秒杀成功，当前抢购成功人数:"
                                + (1-(valInt-100));
                        System.out.println(successMsg);
                         /* 抢购成功业务逻辑 */
                        jedis.setnx(successUserInfo, successMsg);
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            // this.jedis.close();
        }
    }
}

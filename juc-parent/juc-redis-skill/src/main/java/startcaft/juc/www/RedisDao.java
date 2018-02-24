package startcaft.juc.www;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/24 11:24
 */
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    private JedisPool jedisPool;

    public RedisDao(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisDao(String ip,int port){
        this.jedisPool = new JedisPool(ip,port);
    }

    public Seckill getSeckill(long seckillId){
        // redis 获取缓存
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String redisKey = "seckill:" + seckillId;
                // jedis内部并没有实现序列化操作
                // get -> byte[] -> 反序列化 -> Object
                // 采用自定义的序列化方式
                byte[] bytes = jedis.get(redisKey.getBytes());
                if(bytes != null){
                    // 空对象
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                    // 空对象被反序列
                    return seckill;
                }
            }
            finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill){
        // set -> object -> 序列化 -> byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String redisKey = "seckill:" + seckill.getSeckillId();
                byte[] bytes =
                        ProtostuffIOUtil.toByteArray(seckill,schema,
                                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                // 超时缓存 1小时
                int time = 60 * 60;
                String result = jedis.setex(redisKey.getBytes(),time,bytes);
                return result;
            }
            finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }


}

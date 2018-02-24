package juc.framework.branchs;

import org.springframework.jdbc.core.JdbcTemplate;
import java.lang.reflect.ParameterizedType;

/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/22 21:16
 */
public class DefaultTask implements Task<Object> {

    private JdbcTemplate jdbcTemplate;

    public DefaultTask(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Object getResult(String sql) throws Exception {
//        Class<R> clazz = (Class<R>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        R result = jdbcTemplate.queryForObject(sql,clazz);
        if(jdbcTemplate == null){
            throw new Exception("jdbcTemplate is null");
        }
        Object result = jdbcTemplate.queryForObject(sql,Object.class);
        return result;
    }
}

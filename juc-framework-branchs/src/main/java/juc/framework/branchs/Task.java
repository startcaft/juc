package juc.framework.branchs;

/**
 * @Author: pikai
 * @Description: 统计任务接口
 * @Date: Created in 2018/2/22 16:27
 */
public interface Task<R> {

    /**
     * 根据统计分析 SQL 语句进行数据统计
     * @param sql
     * @return
     */
    R getResult(String sql) throws Exception;
}

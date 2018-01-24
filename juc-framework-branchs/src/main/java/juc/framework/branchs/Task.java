package juc.framework.branchs;

/**
 * 任务接口，定义一个无参，返回指定类型的方法 getResult。
 * @param <R>
 */
public interface Task<R> {
    R getResult();
}

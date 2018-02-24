package juc.framework.branchs;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author: pikai
 * @Description: 数据统计任务线程类。
 * @Date: Created in 2018/2/22 16:37
 */
public class TaskThread<R> implements Callable<R> {

    private String taskThreadName;

    private String sql;
    private Task<R> task;

    public TaskThread(String sql, Task<R> task) {
        this.sql = sql;
        this.task = task;
        // 线程名 默认就是 实例名
        this.taskThreadName = this.toString();
    }

    public String getTaskThreadName() {
        return taskThreadName;
    }

    public void setTaskThreadName(String taskThreadName) {
        this.taskThreadName = taskThreadName;
    }

    @Override
    public R call() throws Exception {
        if (task == null){
            return null;
        }
        return task.getResult(this.sql);
    }

    /**
     * FutureTask 用于获取 Callable 接口的返回值
     * @return
     */
    public FutureTask<R> getFutureTask(){
        return new FutureTask<R>(this);
    }
}

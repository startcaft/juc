package juc.framework.branchs;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * 分支线程，用于处理分解业务。
 * @param <R>   具体处理的业务返回最终数据的类型
 */
public class BranchThread<R> implements Callable<R> {

    private Task<R> task;

    private CountDownLatch countDownLatch;

    private String branchThreadName;

    public String getBranchThreadName() {
        return branchThreadName;
    }

    public void setBranchThreadName(String branchThreadName) {
        this.branchThreadName = branchThreadName;
    }

    public BranchThread(Task<R> task) {
        this.task = task;
    }

    @Override
    public R call() throws Exception {
        if (task == null){
            return null;
        }
        return task.getResult();
    }

    public FutureTask<R> getFutureTask(){
        return new FutureTask<R>(this);
    }
}

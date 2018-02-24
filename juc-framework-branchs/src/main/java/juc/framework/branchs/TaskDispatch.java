package juc.framework.branchs;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/22 16:47
 */
public class TaskDispatch {

    private Set<TaskThread> taskThreads;

    public TaskDispatch(Set<TaskThread> taskThreads) {
        this.taskThreads = taskThreads;
    }

    public void doTasks(){
        if(taskThreads != null && taskThreads.size() > 0){
            for (TaskThread taskThread:taskThreads) {
                //创建FutureTask
                FutureTask<?> futureTask=taskThread.getFutureTask();
                //执行任务
                new Thread(futureTask).start();

                try{
                    Object result = futureTask.get();
                    System.out.println("线程:" + taskThread.getTaskThreadName() + "---执行结果:" + result);
                } catch (InterruptedException e) {
                } catch (ExecutionException e) {
                }
            }
        }
    }
}

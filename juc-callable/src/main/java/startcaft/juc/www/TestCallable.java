package startcaft.juc.www;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * Created by startcaft on 2018/1/22.
 */
public class TestCallable {
    public static void main(String[] args){
        CallableDemo cd = new CallableDemo();

        // 执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算的结果。
        // FutureTask 是 Future 的实现类。
        FutureTask<Integer> task = new FutureTask<Integer>(cd);
        new Thread(task).start();

        try {
            // get()方法直到其他所有线程执行完毕才会执行，所以FutureTask也可用于闭锁操作。
            Integer sum = task.get();
            System.out.println("结果:" + sum);
            System.out.println("------------------");
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
    }
}

class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i=0;i<=Integer.MAX_VALUE;i++){
            sum += i;
        }
        return sum;
    }
}
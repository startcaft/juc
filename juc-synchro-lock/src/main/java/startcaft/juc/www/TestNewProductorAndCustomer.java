package startcaft.juc.www;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * jdk 1.5 版本之后的 生产者-消费者模型
 * 使用 Lock 对象替代 synchronized 关键字
 * 使用 Condition 对象来线程间通信（即线程之间的等待唤醒）
 */
public class TestNewProductorAndCustomer {

    public static void main(String[] args) {
        Clerk1 cleak = new Clerk1();

        Productor1 productor = new Productor1(cleak);
        Customer1 customer = new Customer1(cleak);

        new Thread(productor, "生产者A").start();
        new Thread(customer, "消费者A").start();

        new Thread(productor, "生产者B").start();
        new Thread(customer, "消费者B").start();
    }
}


// 店员
class Clerk1 {
    private int productor = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void get(){
        lock.lock();
        try {
            while(productor >= 1){
                System.out.println("仓库已满!");

                try {
                    // 本线程阻塞
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + ++productor);
            // 完成进货逻辑，通知唤醒其他线程。
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }

    }

    public void sale(){
        lock.lock();
        try {
            while(productor <= 0){
                System.out.println("缺货啦!");

                try {
                    // 本线程阻塞
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + --productor);
            // 完成消费逻辑，通知唤醒其他线程。
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }

    }
}

// 生产者
class Productor1 implements Runnable {

    private Clerk1 clerk;

    public Productor1(Clerk1 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            this.clerk.get();
        }

    }
}

// 消费者
class Customer1 implements Runnable {

    private Clerk1 clerk;

    public Customer1(Clerk1 clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            this.clerk.sale();
        }
    }
}
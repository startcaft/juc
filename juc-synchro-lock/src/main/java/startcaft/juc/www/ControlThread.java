package startcaft.juc.www;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 精准控制线程 - 一个互联网公司 14k 起步的面试题
 * 开启三个线程，这个三个线程的ID分别为A，B，C，
 * 每个线程将自己的 ID 在屏幕上打印10变，要求输出的结果必须按照顺序显示
 *  如：ABCABCABCABC......依次循环
 */
public class ControlThread {

    public static void main(String[] args){

        Output output = new Output();

        for(int i = 0;i < 10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    output.printA();
                }
            },"A").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    output.printB();
                }
            },"B").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    output.printC();
                }
            },"C").start();
        }
    }
}


class Output {

    private Lock lock = new ReentrantLock();
    private int flagNumber = 1; // 线程执行的标识，1：A线程执行；2：B线程执行；3：C线程执行
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();


    public void printA(){
        this.lock.lock();
        try {
            // 1.判断是否要阻塞线程(非本线程执行的flagNumber)
            if (flagNumber != 1){
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                }
            }

            // 2.执行逻辑
            System.out.print(Thread.currentThread().getName());

            // 3.唤醒下一个指定的线程
            flagNumber = 2;
            conditionB.signal();
        }
        finally {
            this.lock.unlock();
        }

    }

    public void printB(){
        this.lock.lock();
        try {
            // 1.判断是否要阻塞线程(非本线程执行的flagNumber)
            if (flagNumber != 2){
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                }
            }

            // 2.执行逻辑
            System.out.print(Thread.currentThread().getName());

            // 3.唤醒下一个指定的线程
            flagNumber = 3;
            conditionC.signal();
        }
        finally {
            this.lock.unlock();
        }
    }

    public void printC(){
        this.lock.lock();
        try {
            // 1.判断是否要阻塞线程(非本线程执行的flagNumber)
            if (flagNumber != 3){
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                }
            }

            // 2.执行逻辑
            System.out.println(Thread.currentThread().getName());

            // 3.唤醒下一个指定的线程
            flagNumber = 1;
            conditionA.signal();
        }
        finally {
            this.lock.unlock();
        }
    }
}
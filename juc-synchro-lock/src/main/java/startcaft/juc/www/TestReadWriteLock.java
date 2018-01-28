package startcaft.juc.www;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：读/写分离。
 * 读取的时候是没有线程安全的问题。
 *
 * 读/写互斥；写/写互斥； 这种方式也可以认为是一种乐观锁。
 *
 * 一次只能有一个线程写，一次可以有N个线程读。提高效率
 */
public class TestReadWriteLock {

    public static void main(String[] args){

        ReadWriteLockDemo demo = new ReadWriteLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.setNumber(35);
            }
        }).start();

        for (int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.getNumber();
                }
            }).start();
        }
    }
}


class ReadWriteLockDemo {
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 读
    public void getNumber() {
        this.lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + number);
        }
        finally {
            this.lock.readLock().unlock();
        }
    }

    // 写
    public void setNumber(int number) {
        this.lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        }
        finally {
            this.lock.writeLock().unlock();
        }
    }


}
package startcaft.juc.www;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by startcaft on 2018/1/23.
 */
public class TestLock {

    public static void main(String[] args){

        Lock lock = new ReentrantLock();
        // 100张票，分3个窗口，同时出售
        Ticket ticket = new Ticket(lock);

        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }
}

class Ticket implements Runnable{

    private int ticket = 100;

    private Lock lock;

    public Ticket(Lock lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        while (ticket > 0){
            this.lock.lock();
            try {
                if (ticket > 0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为:" + --ticket);
                }
            }
            finally {
                this.lock.unlock();
            }
        }
    }
}

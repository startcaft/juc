package startcaft.juc.www;

/**
 * 多线程经典的 生产者-消费者模型
 */
public class TestProductorAndCustomer {

    public static void main(String[] args){

        Clerk cleak = new Clerk();

        Productor productor = new Productor(cleak);
        Customer customer = new Customer(cleak);

        new Thread(productor,"生产者A").start();
        new Thread(customer,"消费者A").start();

        new Thread(productor,"生产者B").start();
        new Thread(customer,"消费者B").start();
    }
}

// 店员
class Clerk {
    private int productor = 0;

    public synchronized void get(){
        while(productor >= 1){
            System.out.println("仓库已满!");

            try {
                // 本线程阻塞
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++productor);
        // 完成进货逻辑，通知唤醒其他线程。
        this.notifyAll();
    }

    public synchronized void sale(){
        while(productor <= 0){
            System.out.println("缺货啦!");

            try {
                // 本线程阻塞
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + --productor);
        // 完成消费逻辑，通知唤醒其他线程。
        this.notifyAll();
    }
}

// 生产者
class Productor implements Runnable {

    private Clerk clerk;

    public Productor(Clerk clerk) {
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
class Customer implements Runnable {

    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            this.clerk.sale();
        }
    }
}
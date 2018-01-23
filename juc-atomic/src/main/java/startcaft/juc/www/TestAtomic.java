package startcaft.juc.www;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by startcaft on 2018/1/21.
 */
public class TestAtomic {
    public static void main(String[] args){
        AtomicDemo ad = new AtomicDemo();

        for (int i=0;i<10;i++){
            new Thread(ad).start();
        }
    }
}

class AtomicDemo implements Runnable{

    private volatile AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}

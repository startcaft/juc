package startcaft.juc.www;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 闭锁：在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才能继续执行
 */
public class TestCountDownLatch {

    public static void main(String[] args){
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo ld = new LatchDemo(latch);

        long start = System.currentTimeMillis();
        for(int i=0;i<5;i++){
            new Thread(ld).start();
        }

        try {
            latch.await();      // 主线程等待
        } catch (InterruptedException e) {
        }

        long end = System.currentTimeMillis();

        System.out.println("耗时为:" +  (end - start));
    }
}

class LatchDemo implements Runnable {

    private CountDownLatch lacth;

    public LatchDemo(CountDownLatch latch){
        this.lacth = latch;
    }

    @Override
    public void run() {
        // 可能存在线程安全问题
        synchronized (this){
            try{
                for(int i=0;i<50000;i++){
                    if (i % 2 == 0){
                        System.out.println(i);
                    }
                }
            }
            finally {
                // 其余线程中的lacth.countDown()方法一定要确保执行
                lacth.countDown();
            }
        }
    }
}

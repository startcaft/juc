package startcaft.juc.www;

/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/17 16:54
 */
public class App {

    public static void main( String[] args) {

        test1();// 每次执行的结果都不一样，也有可能有正确的结果。
        test2();// 论执行多少次，返回的最终结果都是1000
    }

    public static void test1(){
        final ShareData data = new ShareData();
        // 开启十个线程，每个线程循环100次
        for (int i = 0; i < 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 暂停1秒，增加并发问题出现的几率
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < 100 ; j++) {
                        data.addCount();
                    }

                    System.out.print(data.getCount() + " ");
                }
            }).start();
        }

        try {
            // 主线程暂停三秒，以保证上面分线程执行完毕。
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + data.getCount());
    }

    public static void test2(){
        final ShareData data = new ShareData();
        // 开启十个线程，每个线程循环100次
        for (int i = 0; i < 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 暂停1秒，增加并发问题出现的几率
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < 100 ; j++) {
                        data.syncAddCount();
                    }

                    System.out.print(data.getCount() + " ");
                }
            }).start();
        }

        try {
            // 主线程暂停三秒，以保证上面分线程执行完毕。
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + data.getCount());
    }
}

/**
 * 共享数据类
 */
class ShareData{
    private int count = 0;

    public void addCount(){
        this.count++;
    }

    public int getCount(){
        return this.count;
    }

    public synchronized  void syncAddCount(){
        this.count++;
    }
}

package startcaft.juc.www;

/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/17 21:34
 */
public class MemoryVisibleTest {

    private static boolean ready;
    private static int number;

    public static class WriterThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 对共享数据进行修改
            number = 100;
            ready = true;
        }
    }

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 读取共享数据
            if(!ready){
                System.out.println(ready);
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args){
        // 从直观上说，程序不会打印 ready 变量
        // 但是结果又很多种可能
        new WriterThread().start();
        new ReadThread().start();
    }
}

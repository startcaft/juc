package startcaft.juc.www;

/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/25 11:27
 */
public class WaitSyncTest {

    /**
     * 程序执行以后，让其暂停一秒，然后再执行
     * 该方法是 同步 的 。
     */
    public synchronized void testWait(){
        System.out.println("Start-----");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End-------");
    }

    // 正常执行
    public static void main(String[] args){
        final WaitSyncTest syncTest = new WaitSyncTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncTest.testWait();
            }
        }).start();
    }
}

package startcaft.juc.www;

/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/25 11:23
 */
public class WaitTest {

    /**
     * 程序执行以后，让其暂停一秒，然后再执行
     */
    public void testWait(){
         System.out.println("Start-----");
         try {
                 wait(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         System.out.println("End-------");
     }

    /**
     * 报错是因为，wait 方法会获取 monitor 对象的所有权，
     * monitor 对象只能在同步的情况下才能获取。
     */
     public static void main(String[] args){
        final WaitTest test = new WaitTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.testWait();
            }
        }).start();
     }
}

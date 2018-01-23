package startcaft.juc.www;


public class TestCompareAndSwap {
    public static void main(String[] args){

        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
        }
    }
}

class CompareAndSwap{
    //内存值
    private int value;

    // 获取内存值
    public synchronized int get(){
        return value;
    }

    // 比较，无论是否成功都会反正内存值
    public synchronized int compareAndSwap(int expectedValue,int newVal){
        int oldValue = value;   // V 获取旧值

        if(oldValue == expectedValue){  // V == A 如果预期值和内存值相等，则赋予新值
            this.value = newVal;
        }

        return oldValue;    // 返回 V 原有的值
    }

    // 设置
    public synchronized boolean compareAndSet(int expectedValue,int newVal){
        return expectedValue == compareAndSwap(expectedValue,newVal);
    }
}

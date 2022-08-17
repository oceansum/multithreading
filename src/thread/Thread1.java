package thread;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/6/17 16:45
 */
public class Thread1 extends Thread{
    private String name;
    public static int a ;
    private static Object obj = new Object();

    public Thread1(String name) {
        this.name = name;
    }

    public Thread1(int a) {
        this.a = a;
    }

    public void run(){
        synchronized (obj) {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + "运行 ：" + i);
//                System.out.println(name + ": a = " + (a + i));
                try {
//                    sleep(100 * 10);
                    obj.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread1 mTh1 = new Thread1("a");
        Thread1 mTh2 = new Thread1("b");
        mTh1.start();
        mTh2.start();
    }
}
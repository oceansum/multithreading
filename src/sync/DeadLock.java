package sync;

import java.util.concurrent.TimeUnit;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/24 19:00
 */
//演示死锁
public class DeadLock {
    //创建两个对象
    static final Object a = new Object();
    static final Object b = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a){
                System.out.println(Thread.currentThread().getName() + " Hold a");
                try {
//                    Thread.sleep(1000);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println(Thread.currentThread().getName() + " Hold b");
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized (b){
                System.out.println(Thread.currentThread().getName() + " Hold b");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println(Thread.currentThread().getName() + " Hold a");
                }
            }

        },"t2").start();
    }
}

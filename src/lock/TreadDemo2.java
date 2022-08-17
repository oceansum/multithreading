package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/10 16:49
 */
//第一步 创建资源类
class Share {
    //初始值
    private int num = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //+1的方法
    public void incr() throws InterruptedException {
        lock.lock();
        //第二步  判断 干活 通知
        try{
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + " :: " + num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //—1的方法
    public void decr() throws InterruptedException {
        lock.lock();
        //第二步  判断 干活 通知
        try{
            while (num != 1) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + " :: " + num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }
}
public class TreadDemo2 {
    public static void main(String[] args) {
        Share share = new Share();
        //第三步  创建线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}

package sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/24 18:37
 */
//可重入锁
public class SyncLockDemo {

    public synchronized void add(){
        add();
    }

    public static void main(String[] args) {

        //StackOverflowError
        //synchronized  可重入锁 可递归调用  导致栈溢出异常
//        new SyncLockDemo().add();

        Object o = new Object();
        new Thread(()->{
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "外层");
                synchronized (o){
                    System.out.println(Thread.currentThread().getName() + "中层");
                    synchronized (o){
                        System.out.println(Thread.currentThread().getName() + "内层");
                    }
                }
            }
        },"t1").start();

        //Lock演示可重入锁
        Lock lock = new ReentrantLock();
        new Thread(()->{
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "外层");
                try{
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "内层");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"t2").start();

        new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "aa");
            lock.unlock();
        },"aa").start();
    }
}

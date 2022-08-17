package juc;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchDemo
 * @Description: 演示CountDownLatch
 * @author: ocean
 * @date: 2022/7/24 23:52
 */
public class CountDownLatchDemo {
    //六个同学陆续地离开教室后  班长锁门
    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);
        //六个同学陆续地离开教室
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " left the classroom");
                //计数  -1
                countDownLatch.countDown();
            },String.valueOf(i)).start();

        }
        //等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " Monitor lock the door!");
    }
}

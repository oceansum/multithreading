package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/10 15:35
 */
//第一步 创建资源类  定义属性和操作方法
class Ticket{
    //票数
    private int number = 30;
    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(true);

    //操作方法 卖票
    public  void sale(){
        //上锁
        lock.lock();
        try {
            //判断是否有票
            if (number > 0){
                System.out.println(Thread.currentThread().getName() + "：买出：第" + (30 - (--number)) + "张。" + "剩余" + number + "张。");
            }
        }finally {
            //解锁
            lock.unlock();
        }

    }

}
public class LSaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //创建三个线程
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"CC").start();
    }
}

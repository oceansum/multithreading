package sync;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/10 14:42
 */
//第一步 创建资源类  定义属性和操作方法
class Ticket{
    //票数
    private int number = 30;
    //操作方法 卖票
    public synchronized void sale(){
        //判断是否有票
        if (number > 0){
            System.out.println(Thread.currentThread().getName() + "：买出：第" + (30 - (--number)) + "张。" + "剩余" + number + "张。");
        }
    }

}

public class SaleTicket {
    //创建多个线程，调用资源类中的方法
    public static void main(String[] args) {
        //创建Ticket对象
        Ticket ticket = new Ticket();
//        ticket.sale();
        //创建三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
//                    System.out.println(Thread.currentThread().getName());
                    ticket.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();
    }
}

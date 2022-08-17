package sync;

import java.util.concurrent.TimeUnit;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/24 16:38
 */
class Phone{
    public  synchronized void sendMes() throws InterruptedException {
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-----sendMes-----");
    }
    public static synchronized void sendEmail(){
        System.out.println("-----sendEmail-----");
    }
    public  void getHello(){
        System.out.println("-----getHello-----");
    }
}
public class LockProblem {
    public static void main(String[] args) throws InterruptedException {

        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(()->{
            try {
                phone.sendMes();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        Thread.sleep(100);

        new Thread(()->{
            phone.sendEmail();
        },"BB").start();

//        new Thread(()->{
//            phone.getHello();
//        },"CC").start();
    }
}

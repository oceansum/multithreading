package sync;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/10 14:04
 */
public class Main {
    public static void main(String[] args) {
        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());//aa::false   false表示是用户线程
            while (true) {

            }
        }, "aa");
        //设置守护线程
        aa.setDaemon(true);

        aa.start();

        System.out.println(Thread.currentThread().getName() + " over");
    }
}

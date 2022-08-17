package sync;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/7/10 15:55
 */
//第一步 创建资源类
class Share {
    //初始值
    private int num = 0;

    //+1的方法
    public synchronized void incr() throws InterruptedException {
        //第二步  判断 干活 通知
        while (num != 0) {//if 会造成虚假唤醒
            this.wait();//wait  在哪睡 就在哪醒
        }

        num++;
        System.out.println(Thread.currentThread().getName() + " :: " + num);

        this.notifyAll();
    }

    //—1的方法
    public synchronized void decr() throws InterruptedException {
        //第二步  判断 干活 通知

        while (num != 1) {
            this.wait();
        }

        num--;
        System.out.println(Thread.currentThread().getName() + " :: " + num);

        this.notifyAll();
    }
}

public class ThreadDemo1 {
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

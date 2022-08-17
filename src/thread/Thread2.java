package thread;

/**
 * @author ocean
 * @version 1.0
 * @date 2022/6/17 17:18
 */


public class Thread2 implements Runnable{
    private String name;

    public Thread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行 ：" + i);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new Thread(new Thread2("c")).start();
        new Thread(new Thread2("d")).start();
    }
}

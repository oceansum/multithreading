package pool;

import java.util.concurrent.*;

/**
 * @ClassName: ThreadPoolDemo2
 * @Description: 自定义线程池
 * @author: ocean
 * @date: 2022/8/17 11:45
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            for (int i = 1; i < 8; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在工作！");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}

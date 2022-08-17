package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: ThreadPoolDemo1
 * @Description: 线程池三种常用分类
 * @author: ocean
 * @date: 2022/8/17 11:13
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //一池五线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        //一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        //可扩容
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        //创建10个任务
        try {
            for (int i = 1; i < 101; i++) {
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在工作！");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool3.shutdown();
        }
    }
}

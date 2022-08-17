package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: CompletableFutureDemo
 * @Description: 异步回调
 * @author: ocean
 * @date: 2022/8/17 16:29
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用  没有返回值
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "voidCompletableFuture");
        });
        voidCompletableFuture.get();
        //异步调用  有返回值
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture");
            int i = 1/0;
            return 1;
        });
        completableFuture.whenComplete((t, u) -> {
            System.out.println("t--->" + t);//返回值
            System.out.println("u--->" + u);//异常信息
        }).get();
    }
}

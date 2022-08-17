package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: Demo1
 * @Description: 比较Runnable和Callable两个接口的区别
 * @author: ocean
 * @date: 2022/7/24 19:31
 */
//实现Runnable接口
class MyThread1 implements Runnable {

    @Override
    public void run() {

    }
}

//实现Callable接口
class MyThread2 implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " come in Callable");
        return null;
    }
}

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Runnable接口创建线程
        new Thread(new MyThread1(), "AA").start();

        //Callable接口 报错 ：Thread构造方法中 没有Callable
//        new Thread(new MyThread2(),"AA").start();

        //FutureTask
        FutureTask futureTask1 = new FutureTask<>(new MyThread2());
        //lam表达式
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in Callable");
            return 0;
        });
        //创建一个线程
        new Thread(futureTask2,"aa").start();
        new Thread(futureTask1,"bb").start();
//        while (!futureTask2.isDone()){
//            System.out.println("wait");
//        }
        //调用future中get方法
        System.out.println(futureTask2.get());
        System.out.println(futureTask1.get());
        System.out.println(Thread.currentThread().getName() + " is over");

    }
}

package forkjoin;

import java.util.concurrent.*;

/**
 * @ClassName: ForkJoinDemo
 * @Description: 分支合并
 * @author: ocean
 * @date: 2022/8/17 14:59
 */
class MyTask extends RecursiveTask<Integer> {

    //拆分插值不超过10，计算10以内运算
    private static final Integer VALUE = 10;
    private int begin;
    private int end;
    private int result;

    //创建有参构造
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {//进一步拆分
            int middle = (begin + end) / 2;
            MyTask task1 = new MyTask(begin, middle);
            MyTask task2 = new MyTask(middle + 1, end);

            task1.fork();
            task2.fork();

            result = task1.join() + task2.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(0,100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        //获取最终合并之后的结果
        Integer result = submit.get();
        System.out.println(result);
        //关闭池对象
        forkJoinPool.shutdown();
    }
}

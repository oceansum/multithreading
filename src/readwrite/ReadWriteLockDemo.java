package readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: ReadWriteLockDemo
 * @Description: 读写锁
 * @author: ocean
 * @date: 2022/8/16 22:13
 */
//资源类
class MyCache {

    //创建map集合
    private volatile Map<String, Object> map = new HashMap<>();

    //创建读写锁对象
    private  ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //写数据
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写");
            TimeUnit.SECONDS.sleep(1);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "=====写完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    //读数据
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读");
            TimeUnit.SECONDS.sleep(1);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "------读完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //创建线程放数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num + "",num);
            },String.valueOf(i)).start();
        }

        //创建携程取数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num + "");
            },String.valueOf(i)).start();
        }
    }


}

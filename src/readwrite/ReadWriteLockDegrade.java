package readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: ReadWriteLockDegrade
 * @Description: 读写锁降级
 * @author: ocean
 * @date: 2022/8/16 22:50
 */
public class ReadWriteLockDegrade {
    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        writeLock.lock();
        System.out.println("==write");

        readLock.lock();
        System.out.println("--read");

        writeLock.unlock();

        readLock.unlock();
    }
}

package own.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 此程序是线程安全的；
 * 可重入锁依靠state来实现内存可见性。
 */
public class ReentrantLockExample {

    private final Lock lock = new ReentrantLock();

    int value;

    void addOne() {
        lock.lock();
        try {
            value += 1;
        } finally {
            lock.unlock();
        }
    }

    void addMillion() {
        for (int i = 0; i < 1000000; i++) {
            this.addOne();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        Thread threadA = new Thread(reentrantLockExample::addMillion);
        Thread threadB = new Thread(reentrantLockExample::addMillion);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(reentrantLockExample.value);
    }
}

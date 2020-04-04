package own.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangzifeng
 */
public class AtomicIntegerDemo {
    static volatile AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(AtomicIntegerDemo::add);
        Thread thread2 = new Thread(AtomicIntegerDemo::add);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(atomicInteger.get());
    }

    static void add() {
        int idx = 0;
        while(idx++ < 10000) {
            atomicInteger.getAndIncrement();
        }
    }

}

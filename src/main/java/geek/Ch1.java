package geek;

/**
 * @author yangzifeng
 */
public class Ch1 {

    // volatile只确保在不同的线程中某个预期（内存）状态为真，也就是只确保在访问此类变量时，新值将立即对所有其他线程可见，
    // 并且执行顺序确保代码处于您期望的状态，count+=1 是多次读写

    private static long count = 0;

    // 当修饰静态方法的时候，锁定的是当前类的 Class 对象
    // 当修饰非静态方法的时候，锁定的是当前实例对象 this
    // 管程中锁的规则，是只保证后续对这个锁的加锁的可见性
    // 受保护资源和锁之间的关联关系是 N:1 的关系

    private synchronized void addOne() {
        count += 1;
    }

    private void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            addOne();
        }
    }
    private static long calc() throws InterruptedException {
        final Ch1 test = new Ch1();
        Thread thread1 = new Thread(() -> test.add10K());
        Thread thread2 = new Thread(() -> test.add10K());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());
    }

}

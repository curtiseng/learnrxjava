package geek;

/**
 * @author yangzifeng
 */
public class Ch1 {


    private static long count = 0;


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

package thread.future;

import java.util.concurrent.*;

/**
 * @author yangzifeng
 */
public class HelloWord {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> name = name();

        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(name);

        System.out.println("sync has commit");

        System.out.println(name.get());

        threadPool.shutdown();
    }

    public static FutureTask<String> name() {
        return new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "tom";
            }
        });
    }
}

package thread.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangzifeng
 */
public class ThreadPool {

    public static void main(String[] args) {

    }

    class UserThreadFactory implements ThreadFactory {

        private final String namePrefix;
        private final AtomicInteger next = new AtomicInteger();

        public UserThreadFactory(String featureOfGroup) {
            this.namePrefix = "From UserThreadFactory's" + featureOfGroup + "-Worker-";
        }

        @Override
        public Thread newThread(Runnable task) {
            String name = namePrefix + next.getAndIncrement();
            Thread thread = new Thread(null, task, name, 0);
            System.out.println(thread.getName());
            return thread;
        }
    }

}

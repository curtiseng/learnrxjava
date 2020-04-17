package jvm;

/**
 *
 *
 * @author yangzifeng
 */
public class JvmArgument {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello gc");
        Thread.sleep(60 * 60 * 1000);
    }
}

package jvm.classloading;

/**
 * @author yangzifeng
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }

    public static int value = 123;
}

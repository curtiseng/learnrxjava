package jvm.classloading;

/**
 * 对于静态字段，只有直接定义这个字段的类才会被初始化。
 * 但子类初始化，父类也会初始化
 * @author yangzifeng
 */
public class NotInitialization {

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}

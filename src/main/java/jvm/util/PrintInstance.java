package jvm.util;

import org.openjdk.jol.info.ClassLayout;

public class PrintInstance {

    public static void printInstance(Object object) {
        ClassLayout classLayout = ClassLayout.parseInstance(object);
        System.out.println(classLayout.toPrintable());
        System.out.println(object.hashCode());
    }

}

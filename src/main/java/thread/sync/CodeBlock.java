package thread.sync;

import org.openjdk.jol.info.ClassLayout;

public class CodeBlock {

    int i = 0;

    void addOne() {
        synchronized (this) {
            i++;
        }
    }

    public static void main(String[] args) {
        CodeBlock codeBlock = new CodeBlock();
        new Thread(codeBlock::addOne, "t1").start();
        new Thread(codeBlock::addOne, "t2").start();
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        ClassLayout classLayout = ClassLayout.parseInstance(codeBlock);
        System.out.println(classLayout.toPrintable());
    }

}

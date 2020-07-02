package leetcode;

/**
 * @author yangzifeng
 */
public class HeapAlgorithm {
    // 数组，从下标1开始存储数据
    private int[] a;
    // 堆中可以存储的最大数据个数
    private int n;
    // 堆中已经存储的数据个数
    private int count;

    public HeapAlgorithm(int capacity) {
        this.a = new int[capacity + 1];
        this.n = capacity;
        this.count = 0;
    }

    void insert(int data) {
        if (count >= n) {
            return;
        }
        ++count;
        a[count] = data;
        int i = count;
        // 大顶堆，如果字节点大于父节点，就往上移动
        // 用数组存一个完全二叉树
        while (i/2 > 0 && a[i] > a[i/2]) {
            int temp = a[i];
            a[i] = a[i/2];
            a[i/2] = temp;
            i = i/2;
        }
    }

    void removeMax() {
        if (count == 0) {return;}
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }
    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPosition = i;
            if (i*2 <= n && a[maxPosition] < a[i*2]) {
                maxPosition = i*2;
            }
            if (i*2+1 <= n && a[maxPosition] < a[i*2+1]) {
                maxPosition = i*2+1;
            }
            if (maxPosition == i) {
                break;
            }
            int temp = a[i];
            a[i] = a[maxPosition];
            a[maxPosition] = temp;
            i = maxPosition;
        }
    }
}

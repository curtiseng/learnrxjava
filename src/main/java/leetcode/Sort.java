package leetcode;

import java.util.Arrays;

/**
 * @author yangzifeng
 */
public class Sort {

    public static void main(String[] args) {
        int[] a = new int[]{7, 9, 11, 10, 6, 3, 8};
        // 这里遍历是0～（length-1）
        quickSort(a,0, a.length-1);
        System.out.println(Arrays.toString(a));
    }

    private static void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = partition(a, start, end);
        quickSort(a, start, i-1);
        quickSort(a, i+1, end);
    }

    private static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int i = start;
        // 遍历后可以保证a[i]之前的数逗比pivot小，但不包括它本身，所以最后需要交换
        for (int j = start; j < end; j++) {
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, end);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

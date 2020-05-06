package leetcode;

import sun.lwawt.macosx.CSystemTray;

import java.util.Arrays;

public class ArrayAlgorithm {


    public static void main(String[] args) {
        int[] nums = new int[] {0, 0, 1, 1, 3};
        moveZeros2(nums);
        System.out.println(Arrays.toString(nums));
    }

    // 双指针，j记录不为0的下一位，也就是下一个不为零的数据应该放的地方
    // i != j,num[i] = 0,是为了把已经遍历过的元素全部变为0
    static void moveZeros(int[] nums) {
        for (int i =0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    static void moveZeros2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
    }

}

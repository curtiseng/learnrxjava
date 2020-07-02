package leetcode;

import sun.lwawt.macosx.CSystemTray;

import java.util.*;

/**
 * @author yangzifeng
 */
public class ArrayAlgorithm {

    public static void main(String[] args) {
        int[] nums = new int[] {0, 0, 1, 1, 3};
        moveZeros2(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 两数之和
     * 如果有序，使用双指针
     */
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 三数之和
     * 双指针，注意三处去重的地方
     * Map与双循环
     * @param nums nums
     */
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(16);
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i-1] == nums[i]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int target = -nums[i];
                if (nums[j] + nums[k] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    k--; j++;
                    while (j < nums.length && nums[j] == nums[j-1]) {
                        j++;
                    }
                    while (k > j && nums[k] == nums[k+1]) {
                        k--;
                    }
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }

    /**
     * 元素置零
     * 双指针，j记录不为0的下一位，也就是下一个不为零的数据应该放的地方
     * i != j,num[i] = 0,是为了把已经遍历过的元素全部变为0
     */
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

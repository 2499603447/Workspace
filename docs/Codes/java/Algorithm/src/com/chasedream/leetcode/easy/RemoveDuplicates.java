package com.chasedream.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = removeDuplicates(arr);
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; ) {
            if (map.containsKey(nums[i])) {
                int temp = nums[i];
                nums[i] = nums[len - 1];
                nums[len - 1] = temp;
                len--;
                continue;
            }

            map.put(nums[i], i);
            i++;
        }
        Arrays.sort(nums, 0, len);

        return len;
    }

    /**
     * 双指针法,空间复杂度为O(1),时间复杂度为O(n)
     *
     * @param nums 输入数组
     * @return 处理后的无重复的数组的长度
     */
    public static int removeDuplicates2(int[] nums) {
        int len = nums.length;
        int p = 0;
        for (int q = 1; q < len; q++) {
            if (nums[q] > nums[p]) {
                int temp = nums[p + 1];
                nums[p + 1] = nums[q];
                nums[q] = temp;
                p++;
            }
        }

        return p + 1;
    }
}

/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.stack;

/**
 * @author Zhang Dezhou
 * @Description leetcode: 面试题59 - I. 滑动窗口的最大值 & 239. 滑动窗口最大值
 * @date 2020/2/15 14:48
 */
public class MaxSlidingWindow {
    public static void main(String[] args) {
        MaxSlidingWindow msw = new MaxSlidingWindow();
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        msw.maxSlidingWindow1(arr, k);
    }

    /**
     * 思路：
     * 暴力
     * 执行用时 :1581 ms, 在所有 Java 提交中击败了6.06%的用户
     * 内存消耗 :54.8 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length + 1 - k];
        for (int i = 0; i + k <= nums.length; i++) {
            res[i] = findMax(nums, i, i + k);
        }
        return res;
    }

    /**
     * 思路：
     * 若划出窗口是最大值，则在新的窗口中重新找最大值
     * 若划出窗口不是最大值，则将划进窗口的值和上一个窗口中的最大值进行比较即可的当前窗口的最大值
     * <p>
     * 执行用时 :2 ms, 在所有 Java 提交中击败了98.71%的用户
     * 内存消耗 :56.2 MB, 在所有 Java 提交中击败了5.05%的用户
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length + 1 - k];
        //res[0] = findMax(nums, 0, k); // 提成方法后算法执行时间开销会增大
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            /*if (max < nums[i]) {
                max = nums[i];
            }*/
            max = Math.max(max, nums[i]);
        }
        res[0] = max;
        for (int i = 1; i < res.length; i++) {
            if (res[i - 1] == nums[i - 1]) {
                //res[i] = findMax(nums, i, k);
                max = Integer.MIN_VALUE;
                for (int j = 0; j < k; j++) {
                    /*if (max < nums[j + i]) {
                        max = nums[j + i];
                    }*/
                    max = Math.max(max, nums[j + i]);
                }
                res[i] = max;
            } else {
                res[i] = Math.max(nums[i + k - 1], res[i - 1]);
            }
        }

        return res;
    }

    private int findMax(int[] arr, int start, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            if (max < arr[start + i]) {
                max = arr[start + i];
            }
        }
        return max;
    }
}

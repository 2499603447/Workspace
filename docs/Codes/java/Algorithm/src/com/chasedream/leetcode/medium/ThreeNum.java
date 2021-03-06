/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import java.util.*;

/**
 * @author Zhang Dezhou
 * @Description leetcode 15. 三数之和
 * @date 2020/1/13 23:26
 */
public class ThreeNum {
    public static void main(String[] args) {
        ThreeNum tn = new ThreeNum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        tn.threeSum(nums);
    }

    /**
     * 先将数组进行排序，选定最小的数，然后在剩余的右边通过双指针法扫描
     *
     * @param nums 输入数组
     * @return 满足三数之和的组合
     * 思路见leetcode.md 思路1
     * 执行用时 :42ms, 在所有 Java 提交中击败了50.87%的用户
     * 内存消耗 :46.6 MB, 在所有 Java 提交中击败了84.11%的用户
     * 执行效率偏低
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum == 0) {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]) ;
                }
            }
        }

        return res;
    }

    /**
     *
     * @param nums 输入数组
     * @return 满足三数之和的组合
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        return res;
    }
}

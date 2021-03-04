/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Zhang Dezhou
 * @Description leetcode: 496. 下一个更大元素 I
 * @date 2020/2/12 21:04
 */
public class NextGreaterElement {
    public static void main(String[] args) {
        NextGreaterElement nge = new NextGreaterElement();
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        nge.nextGreaterElement1(nums1, nums2);
    }

    /**
     * 思路：暴力两层循环
     * <p>
     * 执行用时 :8 ms, 在所有 Java 提交中击败了25.07%的用户
     * 内存消耗 :43.8 MB, 在所有 Java 提交中击败了5.09%的用户
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        boolean flag;
        int temp;
        for (int i = 0; i < nums1.length; i++) {
            temp = -1;
            flag = false;
            for (int val : nums2) {
                if (val == nums1[i]) {
                    flag = true;
                }
                if (flag && val > nums1[i]) {
                    temp = val;
                    break;
                }
            }

            res[i] = temp;
        }
        return res;
    }

    /**
     * 思路：暴力两层循环, 同上，将内部for循环提为findNext方法
     * 时间上有很大的提升，但是没找到具体的提升点
     * <p>
     * 执行用时 :2 ms, 在所有 Java 提交中击败了98.56%的用户
     * 内存消耗 :43.8 MB, 在所有 Java 提交中击败了5.09%的用户
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = findNext(nums2, nums1[i]);
        }
        return res;
    }

    private int findNext(int[] nums2, int target) {
        boolean flag = false;
        for (int val : nums2) {
            if (val == target) {
                flag = true;
            }
            if (flag && val > target) {
                return val;
            }
        }

        return -1;
    }

    /**
     * 思路:
     * 1.从nums2中遍历，找到每个元素后面的第一个最大值
     * 通过stack来实现，将对应的最大值放入map中
     * 2.遍历nums1数组，从map直接get对应的值
     * <p>
     * 执行用时 :5 ms, 在所有 Java 提交中击败了63.97%的用户
     * 内存消耗 :47.7 MB, 在所有 Java 提交中击败了5.09%的用户
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Stack<Integer> stack = new Stack();
        Map<Integer, Integer> nextMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                nextMap.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while (!stack.isEmpty()) {
            nextMap.put(stack.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = nextMap.get(nums1[i]);
        }
        return res;
    }
}

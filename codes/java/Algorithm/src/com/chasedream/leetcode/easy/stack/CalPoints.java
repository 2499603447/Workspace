/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.stack;

import java.util.Stack;

/**
 * @author Zhang Dezhou
 * @Description leetcode: 682. 棒球比赛
 * @date 2020/2/12 22:02
 */
public class CalPoints {
    public static void main(String[] args) {
        CalPoints cp = new CalPoints();
        String[] str = {"5", "-2", "4", "C", "D", "9", "+", "+"};
        cp.calPoints(str);
    }

    /**
     * 执行用时 :4 ms, 在所有 Java 提交中击败了58.49%的用户
     * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了5.24%的用户
     *
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        Stack stack = new Stack();
        int sum = 0;
        int val;
        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case "+":
                    if (!stack.isEmpty()) {
                        val = (int) stack.pop();
                        int temp = val;
                        if (!stack.isEmpty()) {
                            val += (int) stack.peek();
                        }
                        sum += val;
                        stack.push(temp);
                        stack.push(val);
                    }
                    break;
                case "D":
                    if (!stack.isEmpty()) {
                        val = 2 * (int) stack.peek();
                        sum += val;
                        stack.push(val);
                    }
                    break;
                case "C":
                    if (!stack.isEmpty()) {
                        sum -= (int) stack.pop();
                    }
                    break;
                default:
                    val = Integer.valueOf(ops[i]);
                    stack.push(val);
                    sum += val;
            }
        }

        return sum;
    }

    /**
     * 执行用时 :2 ms, 在所有 Java 提交中击败了99.84%的用户
     * 内存消耗 :41.3 MB, 在所有 Java 提交中击败了5.24%的用户
     *
     * @param ops
     * @return
     */
    public int calPoints1(String[] ops) {
        int sum = 0;
        int i = 0;
        int[] nums = new int[ops.length];
        for (int j = 0; j < ops.length; j++) {
            switch (ops[j]) {
                case "+":
                    nums[i] = nums[i - 1] + nums[i - 2];
                    sum += nums[i];
                    i++;
                    break;
                case "D":
                    nums[i] = nums[i - 1] * 2;
                    sum += nums[i];
                    i++;
                    break;
                case "C":
                    sum -= nums[i - 1];
                    i--;
                    break;
                default:
                    nums[i] = Integer.valueOf(ops[j]);
                    sum += nums[i];
                    i++;
            }
        }

        return sum;
    }
}

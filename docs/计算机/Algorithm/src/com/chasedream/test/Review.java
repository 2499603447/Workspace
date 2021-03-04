/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test;

/**
 * @author Zhang Dezhou
 * @Description 此类用于定期复习已经解决过的问题
 * @date 2020/3/26 12:15
 */
public class Review {
    public static void main(String[] args) {
        Review review = new Review();
        int n = 4;
        int[][] arr = {{2, 10}, {3, 1}, {1, 2}, {2, 2}, {3, 5}, {4, 1}, {4, 9}, {2, 7}};
    }

    public int maxArea(int[] height) {
        int max = 0;
        for (int p = 0, q = height.length - 1; p < q; ) {
            int x = q - p;
            int y = height[p] < height[q] ? height[p++] : height[q--];
            max = Math.max(x * y, max);
        }

        return max;
    }
}

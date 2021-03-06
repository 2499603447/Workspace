/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/23 22:41
 */
public class FindTheDistanceValue {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        for (int val1 : arr1) {
            boolean isFind = true;
            for (int val2 : arr2) {
                if (Math.abs(val1 - val2) <= d) {
                    isFind = false;
                    break;
                }
            }

            count += isFind ? 1 : 0;
        }

        return count;
    }
}

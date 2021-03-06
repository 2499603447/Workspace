/*
 * Copyright (c) 2019-2020, Chase Dream All Rights Reserved
 */

package com.chasedream.leetcode.easy.sort;

import java.util.Arrays;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 20-2-17 下午9:49
 */
public class SortArrayByParityII {
    public int[] sortArrayByParityII(int[] A) {
        int[] odds = new int[A.length / 2];
        int[] even = new int[A.length / 2];
        int oddIndex = 0;
        int evenIndex = 0;
        for (int val : A) {
            if (val % 2 == 0) {
                even[evenIndex++] = val;
            } else {
                odds[oddIndex++] = val;
            }
        }
        Arrays.sort(odds);
        Arrays.sort(even);
        int[] res = new int[A.length];
        for (int i = 0; i < A.length / 2; i++) {
            res[i * 2] = even[i];
            res[i * 2 + 1] = odds[i];
        }
        return res;
    }
}

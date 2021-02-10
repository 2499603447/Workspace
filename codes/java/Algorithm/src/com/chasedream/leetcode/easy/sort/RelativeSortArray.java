/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.sort;

import java.util.Arrays;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/18 22:48
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        RelativeSortArray rsa = new RelativeSortArray();
        rsa.relativeSortArray(arr1, arr2);
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length];
        boolean[] isVisited = new boolean[arr1.length];

        int index = 0;
        int subIndex = 0;
        int subLen = arr1.length;
        for (int val2 : arr2) {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] == val2) {
                    res[index++] = arr1[i];
                    isVisited[i] = true;
                    subLen--;
                }
            }
        }
        int[] sub = new int[subLen];
        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                sub[subIndex++] = arr1[i];
            }
        }

        Arrays.sort(sub);
        for (int i = 0; i < subIndex; i++) {
            res[index++] = sub[i];
        }
        return res;
    }

    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        int[] times = new int[1001];
        for (int val : arr1) {
            times[val]++;
        }

        int[] out = new int[arr1.length];
        int index = 0;
        for (int val : arr2) {
            if (times[val] <= 0) {
                continue;
            }

            while (times[val]-- > 0) {
                out[index++] = val;
            }
        }

        for (int i = 0; i < times.length; i++) {
            if (times[i] <= 0) {
                continue;
            }
            while (times[i]-- > 0) {
                out[index++] = i;
            }
        }

        return out;
    }
}

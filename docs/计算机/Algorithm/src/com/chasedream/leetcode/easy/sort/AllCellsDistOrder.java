/*
 * Copyright (c) 2019-2020, Chase Dream All Rights Reserved
 */

package com.chasedream.leetcode.easy.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 20-2-17 下午10:15
 */
public class AllCellsDistOrder {
    public static void main(String[] args) {
        AllCellsDistOrder acd = new AllCellsDistOrder();
        acd.allCellsDistOrder(2, 2, 0, 1);
    }

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[i * C + j][0] = i;
                res[i * C + j][1] = j;
            }
        }

        Arrays.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return Math.abs(ints[0] - r0) + Math.abs(ints[1] - c0) - (Math.abs(t1[0] - r0) + Math.abs(t1[1] - c0));
            }
        });
        return res;
    }
}

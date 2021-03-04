/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/23 22:53
 */
public class MaxNumberOfFamilies {
    public static void main(String[] args) {
        MaxNumberOfFamilies mf = new MaxNumberOfFamilies();
        int n = 4;
        int[][] arr = {{2, 10}, {3, 1}, {1, 2}, {2, 2}, {3, 5}, {4, 1}, {4, 9}, {2, 7}};
        Out.println(mf.maxNumberOfFamilies(n, arr));
    }

    /**
     * 申请一个n*10的数组，记录买过的位置
     * 当n为1000000000，会超出内存限制
     *
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int rows = n;
        boolean[] seats = new boolean[11];
        Map<Integer, List<int[]>> map = Stream.of(reservedSeats).collect(Collectors.groupingBy(x -> x[0]));
        int count = 2 * (n - map.size());
        for (List<int[]> list : map.values()) {
            Arrays.fill(seats, false);
            for (int[] val : list) {
                seats[val[1]] = true;
            }
            for (int i : new int[]{2, 4, 6}) {
                if (seats[i] || seats[i + 1] || seats[i + 2] || seats[i + 3]) {
                    continue;
                }
                seats[i] = seats[i + 1] = seats[i + 2] = seats[i + 3] = true;
                count++;
            }
        }

        return count;
    }
}

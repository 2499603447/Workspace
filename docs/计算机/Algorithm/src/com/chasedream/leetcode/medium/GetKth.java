/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.*;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/23 23:23
 */
public class GetKth {
    public static void main(String[] args) {
        GetKth gk = new GetKth();
        Out.println(gk.getKth(12, 15, 2));
    }
    public int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = lo; i <= hi; i++) {
            map.put(i, dfs(i));
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o1.getValue().equals(o2.getValue()) ?
                o1.getKey() - o2.getKey() : o1.getValue() - o2.getValue());

        for (Map.Entry<Integer, Integer> entry : list) {
            if (k == 1) {
                return entry.getKey();
            }
            k--;
        }

        return 0;
    }

    private int dfs(int n) {
        if (n == 1) {
            return 0;
        }

        return (n & 1) == 1 ? 1 + dfs(3 * n + 1) : 1 + dfs(n / 2);
    }

    private static int[][] num = new int[1001][2];

    /**
     * 静态代码块不算算法的执行时间？
     * 下面代码块放在静态块执行总耗时为 17ms，
     * 而将其放在getKth中耗时 175ms
     */
    static {
        for (int i = 1; i <= 1000; i++) {
            num[i][0] = i;
            num[i][1] = getSteps(i);
        }
    }

    public int getKth1(int lo, int hi, int k) {
        int[][] res = Arrays.copyOfRange(num, lo, hi + 1);
        Arrays.sort(res, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);

        return res[k - 1][0];

    }

    private static int getSteps(int n) {
        int step = 0;
        while (n != 1) {
            n = (n & 1) == 1 ? 3 * n + 1 : n / 2;
            step++;
        }

        return step;
    }
}

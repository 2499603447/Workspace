/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/20 22:36
 */
public class CountPrime {
    public static void main(String[] args) {
        CountPrime cp = new CountPrime();
        cp.countPrimes(10);
    }

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        n -= 1;
        int m = (int) Math.sqrt(n);
        boolean[] isVisited = new boolean[n + 1];
        for (int i = 2; i <= m; i++) {
            int k = 2;
            int temp = i * k;
            while (temp <= n) {
                isVisited[temp] = true;
                temp = i * (++k);
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (!isVisited[i]) {
                count++;
            }
        }

        return count;
    }
}

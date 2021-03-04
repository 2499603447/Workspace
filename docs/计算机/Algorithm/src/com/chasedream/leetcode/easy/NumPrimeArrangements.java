package com.chasedream.leetcode.easy;

import com.chasedream.utils.Out;

import java.util.Arrays;

public class NumPrimeArrangements {
    public static void main(String[] args) {
        NumPrimeArrangements obj = new NumPrimeArrangements();
        Out.println(obj.numPrimeArrangementsEeatosthese(10));
    }

    /**
     * 厄拉多塞筛法
     *
     * @param n 总个数
     * @return 排列组合数
     */
    public int numPrimeArrangementsEeatosthese(int n) {
        // 数组用于记录当前数是否为质数
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                int j = 2;
                int temp = i * j;
                for (; temp <= n; j++) {
                    isPrime[temp] = false;
                    temp = i * j;
                }
            }
        }

        int primeCount = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primeCount++;
            }
        }

        return findPairs(n, primeCount);
    }

    public int numPrimeArrangements(int n) {
        // 所有大于3的质数都分布在6*k的两边，所以先判断小于
        if (n <= 2) {
            return 1;
        }

        // 找到n以内的质数的个数
        int primeCount = 1;
        for (int i = 3; i <= n; i++) {
            if (isPrime(i)) {
                primeCount++;
            }
        }

        return findPairs(n, primeCount);
    }

    /**
     * 传统的判定素数的思路，从2~sqrt（n）看能不能被整除
     *
     * @param k 当前待判定的数
     * @return 素数(true), 非素数(false)
     */
    private boolean isPrime(int k) {
        int len = (int) Math.sqrt(k);
        for (int i = 2; i <= len; i++) {
            if (k % i == 0) {
                return false;
            }
        }

        return k > 2;
    }

    /**
     * 计算所有的组合数= 所有非质数排列组合数 * 所有质数的排列组合数
     *
     * @param n          总个数
     * @param primeCount 质数的个数
     * @return 总的排列个数
     */
    private static int findPairs(int n, int primeCount) {
        long res = 1;
        // 当n < 8时，质数的个数>=非质数的个数.
        int count = n < 8 ? primeCount : n - primeCount;
        //int count = Math.max(primeCount, n - primeCount); // count为质数个数和非质数个数间的最大值
        for (int i = 2; i <= count; i++) {
            res = (res * i) % 1000000007;
            // 这里判断会减少一轮遍历
            // 当i = n - count 时，前面厄res平方.
            if (i == n - count) {
                res = (res * res) % 1000000007;
            }
        }

        return (int) res;
    }
}

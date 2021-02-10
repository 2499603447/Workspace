/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/22 10:55
 */
public class SumFourDivisors {
    public static void main(String[] args) {
        SumFourDivisors sd = new SumFourDivisors();
        int[] nums = {21, 4, 7};
        Out.println(sd.sumFourDivisors1(nums));
    }

    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for (int val : nums) {
            Set<Integer> out = divisors(val);
            if (out.size() == 4) {
                for (int div : out) {
                    sum += div;
                }
            }
        }

        return sum;
    }

    private Set<Integer> divisors(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(n);
        int edge = (int) Math.sqrt(n);
        for (int i = 2; i <= edge; i++) {
            int temp = n % i;
            if (temp == 0) {
                set.add(i);
                set.add(n / i);
            }
        }

        return set;
    }

    public int sumFourDivisors1(int[] nums) {
        int limit = 100000;
        int[] cnt = new int[limit + 1];
        int[] sum = new int[limit + 1];
        for(int i = 1; i <= limit; i++){
            for(int j = i; j <= limit; j += i){
                cnt[j] += 1;
                sum[j] += i;
            }
        }
        int ans = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int x = nums[i];
            if(cnt[x] == 4){
                ans += sum[x];
            }
        }
        return ans;
    }
}

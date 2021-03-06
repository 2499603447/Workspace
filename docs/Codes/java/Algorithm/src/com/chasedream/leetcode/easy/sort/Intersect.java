/*
 * Copyright (c) 2019-2020, Chase Dream All Rights Reserved
 */

package com.chasedream.leetcode.easy.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zhang Dezhou
 * @Description leetcode:350. 两个数组的交集 II
 * @date 20-2-17 下午9:46
 */
public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for (Integer val : nums1) {
            list.add(val);
        }
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int count = 0;
        for(Integer val : nums2){
            if (list.contains(val)) {
                res[count++] = val;
                list.remove(val);
            }
        }

        return Arrays.copyOfRange(res, 0, count);
    }
}

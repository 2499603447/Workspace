/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

import com.chasedream.utils.Out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/22 10:30
 */
public class CreateTargetArray {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        int[] index = {0, 1, 2, 2, 1};
        CreateTargetArray ca = new CreateTargetArray();
        int[] out = ca.createTargetArray1(nums, index);
        for (int val : out) {
            Out.print(val + " ");
        }
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        int[] target = new int[nums.length];
        Arrays.fill(target, -1);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (target[index[i]] == -1) {
                target[index[i]] = nums[i];
            } else {
                int j = index[i];
                while (target[j] != -1) {
                    j++;
                }

                for (int l = j; l > index[i]; l--) {
                    target[l] = target[l - 1];
                }

                target[index[i]] = nums[i];
            }
        }
        return target;
    }

    /**
     * list插入指定位置会自动将后面的元素后移
     *
     * @param nums
     * @param index
     * @return
     */
    public int[] createTargetArray1(int[] nums, int[] index) {
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            ans.add(index[i], nums[i]);
        }
        int[] val = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            val[i] = ans.get(i);
        }
        return val;
    }
}

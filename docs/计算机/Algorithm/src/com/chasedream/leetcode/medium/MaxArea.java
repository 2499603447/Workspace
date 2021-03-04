package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

import java.util.*;

public class MaxArea {
    public static void main(String[] args) {
        MaxArea ma = new MaxArea();
        int[] input = {1, 2};
        Out.println(ma.maxArea2(input));
    }

    /**
     * 暴力解法，类似冒泡排序
     * 其实这题的目的反应到坐标上就是求线段组成的最大面积（前提是,两边的线段要等长）
     *
     * @param height 输入数据
     * @return 最大面积
     * 252 ms	39.8 MB
     */
    public int maxArea1(int[] height) {
        int max = 0;
        int len = height.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int x = j - i;
                int y = height[i] < height[j] ? height[i] : height[j];
                if (x * y > max) {
                    max = x * y;
                }
            }
        }
        return max;
    }

    /**
     * 顶部下沉法，从纵轴往下
     * 其实这题的目的反应到坐标上就是求线段组成的最大面积（前提是,两边的线段要等长）
     *
     * @param height 输入数据
     * @return 最大面积
     * 36 ms	40.4 MB 还是慢
     */
    public int maxArea2(int[] height) {
        int max = 0;
        int len = height.length;
        Map<Integer, Integer[]> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < len; i++) {
            int value = i + 1;
            Integer[] list = map.get(height[i]);
            if (null == list) {
                list = new Integer[2];
                list[0] = value;
                list[1] = value;
            } else {
                if (value > list[0] && value < list[1]) {
                    continue;
                } else if (value < list[0] || list[0] == 0) {
                    list[0] = value;
                } else if (value > list[1]) {
                    list[1] = value;
                }
            }

            map.put(height[i], list);
        }
        int left = 0;
        int right = 0;
        for (Map.Entry<Integer, Integer[]> entry : map.entrySet()) {
            int key = entry.getKey();
            Integer[] list = entry.getValue();
            int curLeft = list[0];
            int curRight = list[1];

            if (left < curLeft && curRight < right) {
                continue;
            }
            left = left == 0 ? curLeft : (curLeft < left && curLeft > 0 ? curLeft : left);
            right = Math.max(curRight, right);
            max = Math.max((right - left) * key, max);
        }
        return max;
    }

    /**
     * 双指针法，每次移动较小长度的指针。
     * 其实这题的目的反应到坐标上就是求线段组成的最大面积（前提是,两边的线段要等长）
     *
     * @param height 输入数据
     * @return 最大面积
     * 2ms	39.3 MB， 能够满足要求
     * 证明：双指针一定能够同时指向最大值
     */
    public int maxArea3(int[] height) {
        int max = 0;
        int len = height.length;
        for (int p = 0, q = len - 1; p < q; ) {
            int x = q - p;
            int y = height[p] < height[q] ? height[p++] : height[q--];
            // 调用Math.max 会比直接通过if... else 判断多1ms的时间开销
            // max = Math.max(x * y, max);
            if (x * y > max) {
                max = x * y;
            }
        }
        return max;
    }
}

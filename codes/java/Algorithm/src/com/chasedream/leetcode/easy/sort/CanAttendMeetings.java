/*
 * Copyright (c) 2019-2020, Chase Dream All Rights Reserved
 */

package com.chasedream.leetcode.easy.sort;

import java.util.*;

/**
 * @author Zhang Dezhou
 * @Description　leetcode:252. 会议室 TODO:掌握最后几种方法
 * @date 20-2-17 下午8:28
 */
public class CanAttendMeetings {

    public static void main(String[] args) {
        CanAttendMeetings cam = new CanAttendMeetings();
        int[][] arr = {{0, 30}, {5, 10}, {15, 20}};
        cam.canAttendMeetings1(arr);
    }

    /**
     * 执行用时 :244 ms, 在所有 Java 提交中击败了7.12%的用户
     * 内存消耗 :66.2 MB, 在所有 Java 提交中击败了5.26%的用户
     *
     * @param intervals 会议室安排数组
     * @return true能参加全部会议
     */
    public boolean canAttendMeetings(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j][0] == intervals[i][0]) {
                    return false;
                }
                if (intervals[j][0] > intervals[i][0]) {
                    if (intervals[i][1] > intervals[j][0]) {
                        return false;
                    }
                } else {
                    if (intervals[j][1] > intervals[i][0]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 执行用时 :20 ms, 在所有 Java 提交中击败了16.46%的用户
     * 内存消耗 :50.7 MB, 在所有 Java 提交中击败了5.26%的用户
     *
     * @param intervals 会议室安排数组
     * @return true能参加全部会议
     */
    public boolean canAttendMeetings1(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            if (map.containsKey(interval[0])) {
                int count = map.get(interval[0]);
                map.put(interval[0], count + 1);
            } else {
                map.put(interval[0], 1);
            }
            if (map.containsKey(interval[1])) {
                int count = map.get(interval[0]);
                map.put(interval[1], count - 1);
            } else {
                map.put(interval[1], -1);
            }
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sum += entry.getValue();
            if (sum > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 执行用时 :7 ms, 在所有 Java 提交中击败了65.84%的用户
     * 内存消耗 :50.6 MB, 在所有 Java 提交中击败了5.26%的用户
     *
     * @param intervals 会议室安排数组
     * @return true能参加全部会议
     */
    public boolean canAttendMeetings2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(ints -> ints[0]));

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 　二叉树:左子树的值都比根节点的值小
     *
     * @param intervals 会议室安排数组
     * @return true能参加全部会议
     */
    public boolean canAttendMeetings3(int[][] intervals) {

        if (intervals.length <= 1) {
            return true;
        }

        SegTree root = new SegTree(intervals[0][0], intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (!insert(root, intervals[i][0], intervals[i][1])) {
                return false;
            }
        }
        return true;
    }

    public boolean insert(SegTree root, int s, int e) {
        if (e <= root.start) {
            if (root.left == null) {
                root.left = new SegTree(s, e);
                return true;
            } else {
                return insert(root.left, s, e);
            }
        } else if (s >= root.end) {
            if (root.right == null) {
                root.right = new SegTree(s, e);
                return true;
            } else {
                return insert(root.right, s, e);
            }
        } else {
            return false;
        }
    }

    public class SegTree {
        int start;
        int end;
        SegTree left;
        SegTree right;

        public SegTree(int s, int e) {
            start = s;
            end = e;
            left = null;
            right = null;
        }
    }

    /**
     * 归并排序
     *
     * @param intervals 会议室安排数组
     * @return true能参加全部会议
     */
    public static boolean canAttendMeetings4(int[][] intervals) {
        if (intervals.length <= 1) {
            return true;
        }
        // 1. 排序
        int[][] aux = new int[intervals.length][];
        sort(intervals, aux, 0, intervals.length - 1);

        // 2. 遍历, 检查前一项的结束时间是否小于下一项的开始时间
        int[] latest = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] - latest[1] < 0) {
                return false;
            }
            latest = intervals[i];
        }

        return true;
    }

    /**
     * 这里我尝试使用归并排序
     */
    private static void sort(int[][] a, int[][] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = (lo + hi) >> 1;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }


    private static void merge(int[][] a, int[][] aux, int lo, int mid, int hi) {
        // for (int i = lo; i <= hi; i++) {
        //     aux[i] = a[i];
        // }
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        int j = lo;
        int k = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (j > mid) {
                a[i] = aux[k++];
            } else if (k > hi) {
                a[i] = aux[j++];
            } else if (aux[k][0] > aux[j][0]) {
                a[i] = aux[j++];
            } else {
                a[i] = aux[k++];
            }
        }
    }

    /**
     * 讲所有的开始和结束时间分别存入数组中，交替比较两个数组的值，从而得到是否有重叠
     *
     * @param intervals 会议室安排数组
     * @return true能参加全部会议
     */
    public static boolean canAttendMeetings5(int[][] intervals) {
        if (intervals.length == 0) {
            return true;
        }
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 0, j = 0, cnt = 0;
        while (i < intervals.length && j < intervals.length) {
            if (start[i] < end[j]) {
                cnt++;
                i++;
            } else if (cnt == 1) {
                j++;
                cnt = 0;
            } else {
                return false;
            }
        }
        return cnt < 2;
    }
}

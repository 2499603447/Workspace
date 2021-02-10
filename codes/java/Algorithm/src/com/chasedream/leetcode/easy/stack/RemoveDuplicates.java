/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.stack;

/**
 * @author Zhang Dezhou
 * @Description leetcode:1047. 删除字符串中的所有相邻重复项
 * @date 2020/2/15 14:06
 */
public class RemoveDuplicates {
    /**
     * 不修改原数组
     * 执行用时 :8 ms, 在所有 Java 提交中击败了92.47%的用户
     * 内存消耗 :45.6 MB, 在所有 Java 提交中击败了5.03%的用户
     *
     * @param S
     * @return
     */
    public String removeDuplicates(String S) {
        if (S == null || S.isEmpty()) {
            return null;
        }
        char[] chs = S.toCharArray();
        char[] arr = new char[S.length()];
        int flag = 0;
        for (int i = 0; i < chs.length; i++) {
            if (flag > 0 && arr[flag - 1] == chs[i]) {
                flag--;
            } else {
                arr[flag++] = chs[i];
            }
        }

        return new String(arr, 0, flag);
    }

    /**
     * 不修改原数组
     * 执行用时 :7 ms, 在所有 Java 提交中击败了93.64%的用户
     * 内存消耗 :45.8 MB, 在所有 Java 提交中击败了5.03%的用户
     *
     * @param S
     * @return
     */
    public String removeDuplicates1(String S) {
        char[] chs = S.toCharArray();
        int flag = 0;
        for (int i = 0; i < chs.length; i++) {
            if (flag > 0 && chs[flag - 1] == chs[i]) {
                flag--;
            } else {
                chs[flag++] = chs[i];
            }
        }

        return new String(chs, 0, flag);
    }
}

/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.string;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/29 18:43
 */
public class Kmp {
    /**
     * 暴力匹配
     *
     * @param s       原字符串
     * @param pattern 匹配的模式串
     */
    public void violentMatch(String s, String pattern) {

    }

    /**
     * KMP 算法
     *
     * @param s
     * @param pattern
     */
    public void kmpMatch(String s, String pattern) {
        int[] lps = new int[pattern.length()];

    }

    private void getLPSArray(String pattern, int[] lps) {
        int m = pattern.length();
        int len = 0;
        int i = 1;
        lps[0] = 0;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = len++;
            } else {
                if (len == 0) {
                    lps[i++] = len;
                } else {

                }
            }
        }

    }
}

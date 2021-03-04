/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import com.chasedream.utils.Out;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/28 23:34
 */
public class LongestWord {
    public static void main(String[] args) {
        LongestWord lw = new LongestWord();
        Out.println(lw.longestWord(new String[]{
                "yo", "ew", "fc", "zrc", "yodn", "fcm", "qm", "qmo", "fcmz", "z", "ewq", "yod", "ewqz", "y"}));
    }

    /**
     * 暴力解法
     *
     * @param words 输入字符串数组
     * @return 最长的连续字符串
     */
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));

        String res = "";
        for (String word : words) {
            res = getString(set, res, word);
        }

        return res;
    }

    private String getString(Set<String> set, String res, String word) {
        if (word.length() > res.length() || word.length() == res.length() && word.compareTo(res) < 0) {
            boolean isFind = true;
            for (int j = 1; j < word.length(); j++) {
                if (!set.contains(word.substring(0, j))) {
                    isFind = false;
                    break;
                }
            }

            if (isFind) {
                res = word;
            }
        }
        return res;
    }

    /**
     * TODO 字典树
     *
     * @param words
     * @return
     */
    public String longestWord1(String[] words) {
        String res = "";
        return res;
    }
}

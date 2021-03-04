/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/27 23:34
 */
public class IndexPairs {
    public static void main(String[] args) {
        IndexPairs pairs = new IndexPairs();
        String text = "thestoryofleetcodeandme";
        String[] words = {"story", "fleet", "leetcode"};
        pairs.indexPairs(text, words);

    }

    /**
     * 通过KMP算法进行字符串匹配
     *
     * @param text 带匹配的字符串
     * @param words 匹配的模式数组
     * @return 匹配成功下标‘起始-结束’对
     */
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> res = new ArrayList<>();
        for (String word : words) {
            res.addAll(check(text, word));
        }

        Collections.sort(res, (o1, o2) -> o1[0] == o2[0] ?
                o1[1] - o2[1] : o1[0] - o2[0]);

        return res.toArray(new int[0][]);
    }

    private List<int[]> check(String s, String pattern) {
        int[] next = new int[pattern.length() + 1];
        getNext(pattern, next);
        int i = -1;
        int j = -1;
        List<int[]> indexPairs = new ArrayList<>();
        while (i < s.length()) {
            if (j == -1 || s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == pattern.length()) {
                indexPairs.add(new int[]{i - j, i - 1});
                j = next[j];
            }
        }

        return indexPairs;
    }

    private void getNext(String pattern, int[] next) {
        int i = 0;
        int j = -1;
        next[0] = -1;
        while (i < pattern.length()) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
    }

    /**
     * 通过indexOf的方式逐个查找
     *
     * @param text 带匹配的字符串
     * @param words 匹配的模式数组
     * @return 匹配成功下标‘起始-结束’对
     */
    public int[][] indexPairs1(String text, String[] words) {
        List<int[]> res = new ArrayList<>();
        for (String word : words) {
            int index = text.indexOf(word);
            while (index != -1) {
                res.add(new int[]{index, index + word.length() - 1});
                index = text.indexOf(word, index + 1);
            }
        }

        Collections.sort(res, (o1, o2) -> o1[0] == o2[0] ?
                o1[1] - o2[1] : o1[0] - o2[0]);

        return res.toArray(new int[0][]);
    }
}

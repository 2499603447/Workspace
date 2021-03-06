/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.*;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/22 19:48
 */
public class WordPattern {
    public static void main(String[] args) {
        WordPattern wp = new WordPattern();
        String pattern = "abba";
        String str = "dog cat cat dog";
        //wp.wordPattern(pattern, str);

        String[] s1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] s2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        /*String[] out = wp.findRestaurant(s1, s2);
        for (String st : out) {
            Out.println(st);
        }*/
        wp.powerfulIntegers(2, 3, 10);
    }

    /**
     * Solution:
     * 通过遍历pattern数组，pattern字符相同的地方，str空格分隔的字符串也要相等。
     * 如：pattern[] 中的i, j位字符相同，则str空格分隔的字符串数组s[] 第i,j 位的字符串也要相等。
     * 不同也要不同。
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        if (pattern.length() != str.split(" ").length) {
            return false;
        }

        char[] p = pattern.toCharArray();
        String[] s = str.split(" ");
        boolean[] isVisited = new boolean[p.length];
        for (int i = 0; i < p.length; i++) {
            if (isVisited[i]) {
                continue;
            }
            isVisited[i] = true;
            for (int j = i + 1; j < p.length; j++) {
                if (p[j] == p[i]) {
                    isVisited[j] = true;
                    if (!s[i].equals(s[j])) {
                        return false;
                    }
                } else if (s[i].equals(s[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int min = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int sum = map.get(list2[i]) + i;
                if (sum == min) {
                    res.add(list2[i]);
                } else if (sum < min) {
                    res.clear();
                    res.add(list2[i]);
                    min = sum;
                }
            }
        }
        return res.toArray(new String[]{});
    }

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> res = new HashSet<>();
        boolean isXEqualOne = false;
        int a = 1;
        for (int i = 0; a <= bound && !isXEqualOne; ) {
            boolean isYEqualOne = false;
            int b = 1;
            for (int j = 0; b <= bound && !isYEqualOne; ) {
                int sum = a + b;
                if (sum <= bound) {
                    res.add(sum);
                }
                if (y == 1) {
                    isYEqualOne = true;
                }
                b = (int) Math.pow(y, ++j);
            }

            if (x == 1) {
                isXEqualOne = true;
            }
            a = (int) Math.pow(x, ++i);
        }

        return new ArrayList<>(res);
    }
}

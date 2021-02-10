/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/3 23:37
 */
public class IsAlienSorted {
    String order;
    public boolean isAlienSorted(String[] words, String order) {
        this.order = order;
        for (int i = 1; i < words.length; i++) {
            if (compareTo(words[i - 1], words[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    private int compareTo(String s1, String s2) {
        int lim = Math.min(s1.length(), s2.length());
        int k = 0;
        while (k < lim) {
            char c1 = s1.charAt(k);
            char c2 = s2.charAt(k);
            if (c1 != c2) {
                return order.indexOf(c1) - order.indexOf(c2);
            }
            k++;
        }
        return s1.length() - s2.length();
    }
}

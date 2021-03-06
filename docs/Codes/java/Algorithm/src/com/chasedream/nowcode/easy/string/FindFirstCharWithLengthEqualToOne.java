/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.nowcode.easy.string;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindFirstCharWithLengthEqualToOne {

    public static void main(String[] args) {
        System.out.println(find("ababaccd"));
    }

    public static char find(String str) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {
                int value = map.get(c);
                map.put(c, value + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return '\0';
    }
}

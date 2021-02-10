/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/1 21:55
 */
public class SubdomainVisits {
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(20);
        for (String str : cpdomains) {
            int i = 0;
            while (str.charAt(i) != ' ') {
                i++;
            }
            int count = Integer.parseInt(str.substring(0, i));
            while (i < str.length()) {
                i++;
                String domain = str.substring(i);
                map.put(domain, map.getOrDefault(domain, 0) + count);
                while (i < str.length() && str.charAt(i) != '.') {
                    i++;
                }
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }

        return res;
    }
}

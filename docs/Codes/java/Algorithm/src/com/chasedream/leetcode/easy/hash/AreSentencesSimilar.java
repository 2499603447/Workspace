/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.*;

/**
 * @author Zhang Dezhou @Description
 * @date 2020/3/1 16:23
 */
public class AreSentencesSimilar {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> list : pairs) {
            Set<String> set0 = map.getOrDefault(list.get(0), new HashSet<>());
            set0.add(list.get(1));
            map.put(list.get(0), set0);

            Set<String> set1 = map.getOrDefault(list.get(1), new HashSet<>());
            set1.add(list.get(0));
            map.put(list.get(1), set1);
        }

        boolean isPair = true;
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].compareTo(words2[i]) != 0
                    && !map.getOrDefault(words1[i], new HashSet<>()).contains(words2[i])) {
                isPair = false;
                break;
            }
        }

        return isPair;
    }
}

/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Dezhou @Description Leetcode:760. 找出变位映射
 * @date 2020/3/1 17:26
 */
public class AnagramMappings {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = map.get(A[i]);
        }
        return res;
    }
}

/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/26 23:35
 */
public class MaxDistance {
    public static void main(String[] args) {
        MaxDistance md = new MaxDistance();
        List<List<Integer>> arrays = new ArrayList<>();
        arrays.add(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }});
        arrays.add(new ArrayList<Integer>() {{
            add(4);
            add(5);
        }});
        arrays.add(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }});
        md.maxDistance(arrays);
    }

    public int maxDistance(List<List<Integer>> arrays) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> listI = arrays.get(i);
            for (int j = 0; j < arrays.size(); j++) {
                List<Integer> listJ = arrays.get(j);
                int diff = Math.max(Math.abs(listI.get(listI.size() - 1) - listJ.get(0)),
                        Math.abs(listJ.get(listJ.size() - 1) - listI.get(0)));
                if (diff > res) {
                    res = diff;
                }
            }
        }

        return res;
    }

    public int maxDistance1(List<List<Integer>> arrays) {
        int res = Integer.MIN_VALUE;
        List<Integer> listI = arrays.get(0);
        int min = listI.get(0);
        int max = listI.get(listI.size() - 1);
        for (int i = 1; i < arrays.size(); i++) {
            listI = arrays.get(i);
            res = Math.max(res, Math.max(Math.abs(listI.get(listI.size() - 1) - min),
                    Math.abs(max - listI.get(0))));
            min = Math.min(min, listI.get(0));
            max = Math.max(max, listI.get(listI.size() - 1));
        }

        return res;
    }
}

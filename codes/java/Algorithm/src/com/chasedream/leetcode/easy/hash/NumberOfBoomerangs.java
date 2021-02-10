/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.HashMap;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/2/23 17:08
 */
public class NumberOfBoomerangs {
    public static void main(String[] args) {
        NumberOfBoomerangs mpa = new NumberOfBoomerangs();
        int[][] in = {{1, 1}, {1, 2}};
        mpa.numberOfBoomerangs(in);
    }

    public int numberOfBoomerangs(int[][] points) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int[] i : points){
            map.clear();
            for(int[] j : points) {
                if (j == i) {
                    continue;
                }

                int d = (int)(Math.pow(i[0] - j[0], 2) + Math.pow(i[1] - j[1], 2));
                if (map.containsKey(d)) {
                    int num = map.get(d);
                    res += num * 2;
                    map.put(d, num + 1);
                } else{
                    map.put(d, 1);
                }
            }
        }

        return res;
    }
}

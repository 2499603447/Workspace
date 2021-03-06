/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.operator;

import com.chasedream.utils.Out;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/21 18:11
 */
public class IsOdd {
    public static void main(String[] args) {
        IsOdd io = new IsOdd();
        long start = System.currentTimeMillis();
        int n = 1000000000;
        int k = 100;
        for (int l = 0; l < k; l++) {
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    io.isOddMod(i);
                }
            }
        }

        Out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        for (int l = 0; l < k; l++) {
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    io.isOddBit(i);
                }
            }
        }

        Out.println(System.currentTimeMillis() - start);
    }

    /**
     * 取模运算实现
     *
     * @return
     */
    private boolean isOddMod(int n) {
        return n % 2 == 1;
    }

    /**
     * 位运算实现
     *
     * @return
     */
    private boolean isOddBit(int n) {
        return (n & 1) == 1;
    }
}

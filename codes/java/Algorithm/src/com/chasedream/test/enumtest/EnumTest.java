/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.enumtest;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/4/19 23:08
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println(Answer.YES); //
        System.out.println(Answer.NO); // 输出是什么，内部发生了什么，可从字节码角度分析
    }
}

enum Answer {
    YES {
        @Override
        public String toString() {
            return "yes";
        }
    },
    NO,
    MAYBE
}

enum ComparisonResult {
    ORDERED_ASCENDING,
    ORDERED_SAME,
    ORDERED_DESCENDING
}

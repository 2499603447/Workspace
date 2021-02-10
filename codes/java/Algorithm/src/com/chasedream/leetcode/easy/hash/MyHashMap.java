/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.LinkedList;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/7 16:19
 */
public class MyHashMap {
    private static final int BASE_SZIE = 1024;
    private Bucket[] buckets;
    /** Initialize your data structure here. */
    public MyHashMap() {
        buckets = new Bucket[BASE_SZIE];
        for (int i = 0; i < BASE_SZIE; i++) {
            buckets[i] = new Bucket();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = hash(key);
        buckets[index].put(value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return 0;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {

    }

    private int hash(int key) {
        return key % BASE_SZIE;
    }
    class Bucket {
        private LinkedList<Integer> mList;
        Bucket() {
            mList = new LinkedList<>();
        }

        public void put(Integer val) {

        }

        public int get(Integer val) {
            for (Integer data : mList) {
                if (val.equals(data)) {
                    return data;
                }
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            mList.remove(key);
        }
    }
}

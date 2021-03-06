/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.leetcode.easy.hash;

import java.util.LinkedList;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/3/4 22:04
 */
public class MyHashSet {
    // 单独链表发实现
    private static final int BASE_SZIE = 1024;
    private Bucket[] buckets;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        buckets = new Bucket[BASE_SZIE];
        for (int i = 0; i < BASE_SZIE; i++) {
            buckets[i] = new Bucket();
        }
    }

    public void add(int key) {
        int index = hash(key);
        buckets[index].add(key);
    }

    public void remove(int key) {
        int index = hash(key);
        buckets[index].remove(key);
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int index = hash(key);
        return buckets[index].contains(key);
    }

    private int hash(int key) {
        return key % BASE_SZIE;
    }

    class Bucket {
        private LinkedList<Integer> container;

        public Bucket() {
            container = new LinkedList<>();
        }

        private void add(Integer key) {
            if (!contains(key)) {
                container.addFirst(key);
            }
        }

        private void remove(Integer key) {
            container.remove(key);
        }

        private boolean contains(Integer key) {
            return container.contains(key);
        }
    }
}

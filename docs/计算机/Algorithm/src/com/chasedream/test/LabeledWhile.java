/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test;

// control/LabeledWhile.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// "While" with "labeled break" and "labeled continue."

import java.util.Collections;
import java.util.List;

public class LabeledWhile {
    int m;
    int n;
    int k;
    int[][] grid;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        grid = new int[m][n];

        int count = dfs(0, 0, 0, 0);
        return count;
    }

    private int dfs(int r, int c, int sr, int sc) {
        if (r < 0 || r >= m || c < 0 || c >= n || sr + sc > k || grid[r][c] == 1) {
            return 0;
        }

        grid[r][c] = 1;

        return 1 + dfs(r + 1, c, (r + 1) % 10 == 0 ? sr - 8 : sr + 1, sc) +
                dfs(r - 1, c, (r - 1) % 10 == 0 ? sr + 8 : sr - 1, sc) +
                dfs(r, c + 1, sr, (c + 1) % 10 == 0 ? sc - 8 : sc + 1) +
                dfs(r, c - 1, sr, (c - 1) % 10 == 0 ? sc + 8 : sc - 1);
    }

    public static void main(String[] args) {
        LabeledWhile lw = new LabeledWhile();
        lw.movingCount(4, 6, 15);

        // HashMap可以的键值可以是null, "".
        List<String> stringList = Collections.nCopies(100, "geeks");
        stringList.add("hi");
        int i = 0;
        outer:
        while (true) {
            System.out.println("Outer while loop");
            while (true) {
                i++;
                System.out.println("i = " + i);
                if (i == 1) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("continue outer");
                    continue outer;
                }
                if (i == 5) {
                    System.out.println("break");
                    break;
                }
                if (i == 7) {
                    System.out.println("break outer");
                    break outer;
                }
            }
        }
    }
}
package com.chasedream.leetcode.easy;

import com.chasedream.utils.Out;

public class OddCells {
    public static int oddCells(int n, int m, int[][] indices) {
        int[][] arr = new int[n][m];
        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < m; j++) {
                arr[indices[i][0]][j]++;
            }

            for (int j = 0; j < n; j++) {
                arr[j][indices[i][1]]++;
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] % 2 == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        /*int n = 2;
        int m = 3;
        int[][] arr = new int[][]{{0,1},{1,1}};*/
        int n = 2;
        int m = 2;
        int[][] arr = new int[][]{{1,1},{0,0}};
        Out.println(oddCells(n, m ,arr));
    }
}

package com.chasedream.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class ReconstructMatrix {
    public static List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> listUpper = new ArrayList<>();
        List<Integer> listLower = new ArrayList<>();
        for (int i : colsum) {
            if (i == 2) {
                upper--;
                lower--;
            }
        }

        for (int i : colsum) {
            if (i == 2) {
                listUpper.add(1);
                listLower.add(1);
            } else if (i == 0) {
                listUpper.add(0);
                listLower.add(0);
            } else {
                if (upper > 0) {
                    listUpper.add(1);
                    listLower.add(0);
                    upper--;
                } else {
                    listUpper.add(0);
                    listLower.add(1);
                    lower--;
                }

            }
        }
        if (upper == 0 && lower == 0) {
            result.add(listUpper);
            result.add(listLower);
        }
        return result;
    }

    public static void main(String[] args) {
        /*int upper = 2;
        int lower = 1;
        int[] colums = new int[]{1, 1, 1};*/
        int upper = 5;
        int lower = 5;
        int[] colums = new int[]{2,1,2,0,1,0,1,2,0,1};
        /*int upper = 2;
        int lower = 3;
        int[] colums = new int[]{2,2,1,1};*/
        List<List<Integer>> out = reconstructMatrix(upper, lower, colums);
        for (List<Integer> list : out) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }
}

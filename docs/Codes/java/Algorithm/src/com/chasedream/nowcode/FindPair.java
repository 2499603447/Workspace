package com.chasedream.nowcode;

import java.util.*;

public class FindPair {
    public static void main(String[] args) {
        //System.out.println(countPairs(new int[]{1, 2, 2, 3, 4, 5}, 6, 6));

        int[] out = exchangeAB(new int[]{1, 2});
        for (int i = 0; i < out.length; i++) {
            System.out.println(out[i]);
        }
    }

    public static int[] exchangeAB(int[] AB) {
        AB[0] = AB[0] + AB[1];
        AB[1] = AB[0] - AB[1];
        AB[0] = AB[0] - AB[1];
        return AB;
    }

    public static int countPairs(int[] A, int n, int sum) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;
        for (int i=0; i<n; i++)
        {
            count += getCount(hashMap, sum - A[i]);
            hashMap.put(A[i], getCount(hashMap, A[i]) + 1);
        }
        return count;
    }

    private static Integer getCount(HashMap<Integer, Integer> hashMap, int word){
        Integer get = hashMap.get(word);
        return get == null ? 0 : get;
    }

    @Override
    public String toString() {
        // return super.toString();

        return "FindPair:" + this + "\n"; // 无意识的递归调用
    }
}

package com.chasedream.nowcode.easy.array;

import java.util.Scanner;

public class MaxSubArray {
    public static int maxSubArray(int[] arr) {
        int maxSum = arr[0];
        int thisSum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (thisSum + arr[i] > arr[i]) {
                thisSum += arr[i];
            } else {
                thisSum = arr[i];
            }

            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nums = in.nextInt();
        int[] arr = new int[nums];
        for (int i = 0; i < nums; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(maxSubArray(arr));
    }
}

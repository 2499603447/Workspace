package com.chasedream.leetcode.medium;

public class FractionToDecimal {
    public static void main(String[] args) {
        FractionToDecimal test = new FractionToDecimal();
        test.fractionToDecimal(4, 333);
    }

    public String fractionToDecimal(int numerator, int denominator) {
        int fraction = numerator / denominator;
        int decimal = numerator % denominator;
        StringBuilder builder = new StringBuilder();
        builder.append(fraction);
        if (decimal == 0) {
            return builder.toString();
        }

        builder.append(".");
        int temp;
        int lastDecimal = decimal;
        while (decimal != 0) {
            temp = decimal * 10;
            fraction = temp / denominator;
            decimal = temp % denominator;
            if (decimal == lastDecimal) {
                builder.append("(" + fraction + ")");
                break;
            }

            builder.append(fraction);
        }

        return builder.toString();
    }
}

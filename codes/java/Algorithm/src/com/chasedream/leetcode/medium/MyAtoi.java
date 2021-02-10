package com.chasedream.leetcode.medium;

import com.chasedream.utils.Out;

/**
 * 8. 字符串转换整数 (atoi)
 */
public class MyAtoi {
    public static void main(String[] args) {
        MyAtoi atoi = new MyAtoi();
        Out.println(atoi.myAtoi2("-"));
    }

    /**
     * 字符串解法，
     * 没有完全理解题目的意思，逻辑稍微复杂一点
     *
     * @param str 输入字符串
     * @return 输出的整数
     * 5ms
     */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 去除首位空字符
        String input = str.trim();
        if (input.length() == 0) {
            return 0;
        }
        String resultStr = getIntString(input);
        int result = 0;
        if (resultStr.equals("") || resultStr.equals("+") || resultStr.equals("-")) {
            return 0;
        }
        char ch = resultStr.charAt(0);
        // 通过捕获异常处理溢出
        try {
            result = Integer.valueOf(resultStr);
        } catch (NumberFormatException e) {
            if (ch == '-') {
                result = Integer.MIN_VALUE;
            } else {
                result = Integer.MAX_VALUE;
            }
        }

        return resultStr == "" ? 0 : result;
    }

    private String getIntString(String input) {
        int len = input.length();
        char[] chars = input.toCharArray();
        if ((chars[0] != '+' && chars[0] != '-' && !isNumber(chars[0]))) {
            return "";
        }
        int start = 0;
        for (int i = 0; i < len; i++) {
            // 找到第一个+，- 或者数字
            if ((chars[i] == '+'
                    || chars[i] == '-' || isNumber(chars[i]))) {
                start = i;
                break;
            }
        }
        int end = start;
        for (int i = start + 1; i < len; i++) {
            // 找到第一个不为数字的字符
            if (!isNumber(chars[i])) {
                end = i;
                break;
            }
        }
        end = end == start ? len : end;

        return input.substring(start, end);
    }

    /**
     * 参考String.trim方法
     *
     * @param str 输入字符串
     * @return 输出的整数
     * 慢
     * 执行用时 :3 ms, 在所有 Java 提交中击败了60.05%的用户
     * 内存消耗 :36.3 MB, 在所有 Java 提交中击败了83.89%的用户
     */
    public int myAtoi1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int len = str.length();
        int st = 0;
        char[] value = str.toCharArray();
        // 找到第一个非空格字符
        while ((st < len) && (value[st] <= ' ')) {
            st++;
        }

        // 判断st位置的字符是否合法，即是否为+/-/数字
        if (st >= len || !isNumber(value[st]) && (value[st] != '+') && (value[st] != '-')) {
            return 0;
        }
        // 接着从st + 1的位置向后找第一个不为数字的字符位置
        int end = st + 1;
        while ((end < len) && (isNumber(value[end]))) {
            end++;
        }

        String resultStr = str.substring(st, end);
        if (resultStr.equals("+") || resultStr.equals("-")) {
            return 0;
        }
        int result = 0;
        char ch = resultStr.charAt(0);
        // 通过捕获异常处理溢出
        try {
            result = Integer.valueOf(resultStr);
        } catch (NumberFormatException e) {
            if (ch == '-') {
                result = Integer.MIN_VALUE;
            } else {
                result = Integer.MAX_VALUE;
            }
        }

        return result;
    }

    /**
     * 在方法2的基础上更换溢出处理方式， 通过和 Integer.MAX_VALUE / 10进行比较
     *
     * @param str 输入字符串
     * @return 输出的整数
     * 有1ms的提升
     * 执行用时 :2 ms, 在所有 Java 提交中击败了98.91%的用户
     * 内存消耗 :36 MB, 在所有 Java 提交中击败了86.24%的用户
     */
    public int myAtoi2(String str) {
        if (str.length() == 0) {
            return 0;
        }

        int len = str.length();
        int st = 0;
        char[] value = str.toCharArray();
        // 找到第一个非空格字符
        while ((st < len) && (value[st] <= ' ')) {
            st++;
        }
        // 判断st位置的字符是否合法，即是否为+/-/数字
        if (st >= len || !isNumber(value[st]) && (value[st] != '+') && (value[st] != '-')) {
            return 0;
        }
        // 接着从st + 1的位置向后找第一个不为数字的字符位置
        int result = 0;
        boolean isNegative = false;
        boolean isFirstNum = true;
        int temp = Integer.MAX_VALUE / 10; // 提取溢出比较数，避免在while循环中不断地开辟空间
        // 判断当前字符是否为+/-号，满足则st+1
        if (value[st] == '-') {
            isNegative = true; // 负数标志位置为true
            st++;
        } else if (value[st] == '+') {
            st++;
        }

        //遇到第一个不是数字的字符退出while循环
        while ((st < len) && (isNumber(value[st]))) {
            // 溢出处理的关键步骤
            if (result > temp || (result == temp && value[st] > '7')) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + value[st] - '0';
            st++;
        }

        return isNegative ? ~result + 1 : result;
    }

    /**
     * 判断当前字符是否为数字
     *
     * @param ch 当前字符
     * @return 是数字-true, 不是-false
     */
    private boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }
}

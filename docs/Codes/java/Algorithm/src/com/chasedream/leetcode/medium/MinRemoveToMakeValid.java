package com.chasedream.leetcode.medium;

import java.util.*;

public class MinRemoveToMakeValid {
    static class Operator {
        char c;
        int index;

        Operator(char c, int index) {
            this.c = c;
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Operator) {
                return ((Operator) obj).c == c && ((Operator) obj).index == index;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(c, index);
        }
    }

    public static String minRemoveToMakeValid(String s) {
        long start = System.currentTimeMillis();
        Stack<Operator> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(new Operator('(', i));
            } else if (chars[i] == ')') {
                if (stack.isEmpty()) {
                    stack.push(new Operator(')', i));
                    continue;
                }
                Operator operator = stack.peek();
                if (operator.c == '(') {
                    stack.pop();
                } else {
                    stack.push(new Operator(')', i));
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder(s);
        Iterator iterator = stack.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Operator operator = (Operator) iterator.next();
            builder.replace(operator.index - i, operator.index - i + 1, "");
            i++;
            iterator.remove();
        }
        System.out.println(System.currentTimeMillis() - start);

        return builder.toString();
    }

    public static String minRemoveToMakeValid2(String s) {
        long start = System.currentTimeMillis();
        int[] operators = new int[s.length()];
        Arrays.fill(operators, -1);
        int index = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                operators[index++] = i;
            } else if (chars[i] == ')') {
                if (index == 0) {
                    operators[index++] = i;
                    continue;
                }
                char ch = chars[operators[index - 1]];
                if (ch == '(') {
                    operators[--index] = -1;
                } else {
                    operators[index++] = i;
                }
            }
        }
        //System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder(s);
        Arrays.sort(operators);
        int k = 0;
        for (int i = operators.length - 1; i >= 0 && operators[i] >= 0; i--) {
            builder.replace(operators[i], operators[i] + 1, "");
        }
        System.out.println("method-2:" + (System.currentTimeMillis() - start));

        return builder.toString();
    }

    public static String minRemoveToMakeValid3(String s) {
        long start = System.currentTimeMillis();
        char[] chars = s.toCharArray();
        int l = chars.length;
        int count = 0;
        for (int i = 0; i < l; i++) {
            switch (chars[i]) {
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
            }
            if (count < 0) {
                count = 0;
                chars[i] = 0x00;
            }
        }
        count = 0;
        for (int i = l - 1; i >= 0; i--) {
            switch (chars[i]) {
                case ')':
                    count++;
                    break;
                case '(':
                    count--;
                    break;
            }
            if (count < 0) {
                count = 0;
                chars[i] = 0x00;
            }
        }
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            if (c != 0x00) result.append(c);
        }
        System.out.println("method-3:" + (System.currentTimeMillis() - start));
        return result.toString();
    }

    public static String minRemoveToMakeValid4(String s) {
        long start = System.currentTimeMillis();
        char[] cs = s.toCharArray();
        int remove = 0;
        int left = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '(') {
                left++;
            } else if (cs[i] == ')') {
                if (left == 0) {
                    remove++;
                    cs[i] = '\0';
                } else {
                    left--;
                }
            }
        }
        int right = 0;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] == '(') {
                if (right == 0) {
                    remove++;
                    cs[i] = '\0';
                } else {
                    right--;
                }
            } else if (cs[i] == ')') {
                right++;
            }
        }
        char[] res = new char[cs.length - remove];
        int index = 0;
        for (char c : cs) {
            if (c != '\0') {
                res[index++] = c;
            }
        }
        System.out.println("method-4:" + (System.currentTimeMillis() - start));
        return new String(res);
    }

    public static void main(String[] args) {
        /*StringBuilder temp = new StringBuilder();
        Random random = new Random();
        while (temp.length() < 100000) {
            char ch = (char) (random.nextInt(124));
            if (ch == '(' || ch == ')' || (ch > 'a' && ch < 'z')) {
                temp.append(ch);
            }
        }*/
        String[] str = new String[]{"lee(t(c)o)de)", "a)b(c)d",
                "))((", "(a(b(c)d)", "(t(e)y))d(()(e(",
        //        temp.toString()
        };

        for (int i = 0; i < 100; i++) {
            StringBuilder temp = new StringBuilder();
            Random random = new Random();
            while (temp.length() < 100000) {
                char ch = (char) (random.nextInt(124));
                if (ch == '(' || ch == ')' || (ch > 'a' && ch < 'z')) {
                    temp.append(ch);
                }
            }
            minRemoveToMakeValid2(temp.toString());
            minRemoveToMakeValid3(temp.toString());
            minRemoveToMakeValid4(temp.toString());
            System.out.println("----");

            /*System.out.println(minRemoveToMakeValid3(str[5]));
            System.out.println(minRemoveToMakeValid4(str[5]));*/
        }
    }
}

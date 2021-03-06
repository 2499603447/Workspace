package cn.chasedream.stringtest;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*String A = "ABCDEFGH";
        System.out.println(A.substring(0,5));
        System.out.println(A.substring(5,8));
        Map<String, String[]> map = new HashMap<>();*/
        //System.out.println(findMaxDivision(new int[] {9,3,1,10}, 4));
        //System.out.println(findAppearance("acbc", 4, "bc", 2));

        //System.out.println(convert("A", 1));
        //System.out.println(formatString("A%sC%sE", 7, new char[]{'B', 'D', 'F'}, 3));
        //findMaxScoreMethod();

        System.out.println(divide(-2147483648, -1));
    }

    public  static int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        String prefix = "";

        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            prefix = "-";
        }

        int result = 0;
        int i = 0;
        for (; result <= dividend; i++) {
            result += divisor;
        }

        return Integer.parseInt(prefix + (--i));
    }

    /**
     * 题目描述
     * 小Q得到一个神奇的数列: 1, 12, 123,...12345678910,1234567891011...。
     *
     * 并且小Q对于能否被3整除这个性质很感兴趣。
     *
     * 小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
     *
     * 输入描述:
     * 输入包括两个整数l和r(1 <= l <= r <= 1e9), 表示要求解的区间两端。
     * 输出描述:
     * 输出一个整数, 表示区间内能被3整除的数字个数。
     * 示例1
     * 输入
     * 2 5
     * 输出
     * 3
     *
     * https://www.nowcoder.com/practice/51dcb4eef6004f6f8f44d927463ad5e8?tpId=98&tqId=32825&tPage=1&rp=1&ru=/ta/2019test&qru=/ta/2019test/question-ranking
     */
    public static void divideBy3(){
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" ");
        int l = Integer.parseInt(input[0]);
        int r = Integer.parseInt(input[1]);
        int num = 0;
        long sum = l * (l - 1) / 2;

        for (int i = l; i <= r; i++) {
            sum += i;
            if ((sum % 3) == 0) {
                num++;
            }
        }

        System.out.println(num);
    }

    /**
     * 请设计一个复杂度为O(n)的算法，计算一个未排序数组中排序后相邻元素的最大差值。
     * <p>
     * 给定一个整数数组A和数组的大小n，请返回最大差值。保证数组元素个数大于等于2小于等于500。
     *
     * @param A [9,3,1,10]
     * @param n 4
     * @return 6
     */
    public static int findMaxDivision(int[] A, int n) {
        Arrays.sort(A);
        int maxDiff = 0;
        for (int i = 1; i < n; i++) {
            int diff = Math.abs(A[i] - A[i - 1]);
            maxDiff = diff < maxDiff ? maxDiff : diff;
        }

        return maxDiff;
    }

    /**
     * 对于两个字符串A，B。请设计一个高效算法，找到B在A中第一次出现的起始位置。若B未在A中出现，则返回-1。
     * <p>
     * 给定两个字符串A和B，及它们的长度lena和lenb，请返回题目所求的答案。
     * <p>
     * 测试样例：
     * <p>
     * 返回：2
     *
     * @param A    "acbc"
     * @param lena 4
     * @param B    "bc"
     * @param lenb 2
     * @return
     */
    public static int findAppearance(String A, int lena, String B, int lenb) {
        if (lenb > lena || !A.contains(B)) {
            return -1;
        }

        return A.indexOf(B);
    }

    /**
     * 题目描述
     * 请你实现一个简单的字符串替换函数。原串中需要替换的占位符为"%s",请按照参数列表的顺序一一替换占位符。若参数列表的字符数大于占位符个数。则将剩下的参数字符添加到字符串的结尾。
     *
     * 给定一个字符串A，同时给定它的长度n及参数字符数组arg，请返回替换后的字符串。保证参数个数大于等于占位符个数。保证原串由大小写英文字母组成，同时长度小于等于500。
     *
     * 测试样例：
     * "A%sC%sE",7,['B','D','F']
     * 返回："ABCDEF"
     *
     * @param A     "A%sC%sE"
     * @param n     7
     * @param arg   ['B','D','F']
     * @param m     3
     * @return      ABCDEF
     */
    public static String formatString(String A, int n, char[] arg, int m) {
        String[] strs = A.split("%s");
        int len = strs.length;
        String result = "";
        for (int i = 0; i < len; i++) {
            result += strs[i] + arg[i];
        }

        int left = m - len;
        for (int i = 0; i < left; i++) {
            result += arg[i + len];
        }

        return result;
    }

    /**
     * 题目描述
     * 老师想知道从某某同学当中，分数最高的是多少，现在请你编程模拟老师的询问。当然，老师有时候需要更新某位同学的成绩.
     * 输入描述:
     * 输入包括多组测试数据。
     * 每组输入第一行是两个正整数N和M（0 < N <= 30000,0 < M < 5000）,分别代表学生的数目和操作的数目。
     * 学生ID编号从1编到N。
     * 第二行包含N个整数，代表这N个学生的初始成绩，其中第i个数代表ID为i的学生的成绩
     * 接下来又M行，每一行有一个字符C（只取‘Q’或‘U’），和两个正整数A,B,当C为'Q'的时候, 表示这是一条询问操作，他询问ID从A到B（包括A,B）的学生当中，成绩最高的是多少
     * 当C为‘U’的时候，表示这是一条更新操作，要求把ID为A的学生的成绩更改为B。
     * 输出描述:
     * 对于每一次询问操作，在一行里面输出最高成绩.
     *
     * 示例1
     * 输入：
     * 5 7
     * 1 2 3 4 5
     * Q 1 5
     * U 3 6
     * Q 3 4
     * Q 4 5
     * U 4 5
     * U 2 9
     * Q 1 5
     *
     * 输出：
     * 5
     * 6
     * 5
     * 9
     */
    private static void findMaxScoreMethod(){
        long startTime = System.currentTimeMillis();
        findMaxScoreMethod1();
        System.out.println(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        findMaxScoreMethod2();
        System.out.println(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        findMaxScoreMethod3();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    /**
     * 暴力破解
     * 每次更新直接更，复杂度为 O(1)
     * 每次删除区间遍历，复杂度为 O(n)
     */
    private static void findMaxScoreMethod1(){
        Scanner s = new Scanner(System.in);
        String[] inputs = s.nextLine().split(" ");
        int nums = Integer.parseInt(inputs[0]);
        int optTimes = Integer.parseInt(inputs[1]);

        int[] scores = new int[nums + 1];
        String[] s_scores = s.nextLine().split(" ");
        for (int i = 1; i <= nums; i++) {
            scores[i] = Integer.parseInt(s_scores[i - 1]);
        }

        while (s.hasNext()){
            String[] in = s.nextLine().split(" ");
            if (in[0].equals("Q")) {
                System.out.println(findMax(scores, Integer.parseInt(in[1]), Integer.parseInt(in[2])));
            } else if (in[0].equals("U")) {
                scores[Integer.parseInt(in[1])] = Integer.parseInt(in[2]);
            }
        }
    }

    private static int findMax(int[] score, int start, int end) {
        int max = score[start];
        for (int i = start + 1; i <= end; i++) {
            max = max > score[i] ? max : score[i];
        }

        return max;
    }

    /**
     * 线段树
     */
    private static void findMaxScoreMethod2(){

    }

    /**
     * 块状链表
     */
    private static void findMaxScoreMethod3(){

    }


    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     *
     * @param s       LEETCODEISHIRING
     * @param numRows 3
     * @return LCIRETOESIIGEDHN
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        String[] strs = new String[numRows];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = "";
        }
        int len = s.length();
        int dim = 2 * numRows - 2;
        for (int i = 0; i < len; i++) {
            int left = i % dim;
            int pos;
            if (left < numRows - 1) {
                pos = left;
            } else {
                pos = numRows - 1 - left % (numRows - 1);
            }

            strs[pos] += (s.charAt(i));
        }

        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            result.append(str);
        }

        return result.toString();
    }
}

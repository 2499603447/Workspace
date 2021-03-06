package com.chasedream.leetcode.medium;

public class MinimumSwap {

    /**
     * 一趟遍历，相同则跳过
     * 当不相同时，再从剩余的里面能否找到与当前s1,s2相同的或者相异的（xy，yx或yx，xy的这种）
     * 若找不出，则返回-1
     * 若能找出：1.相同：次数+1，2.不同：次数+2
     * 继续上述遍历
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 交换的次数
     */
    private static int minimumSwap(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int len = s1.length();
        int times = 0;
        boolean isFindSame;
        for (int i = 0; i < len; i++) {
            isFindSame = false;
            if (c1[i] == c2[i]) {
                continue;
            }
            int index = -1;
            for (int j = i + 1; j < len; j++) {
                if (c1[j] == c2[i] && c1[i] == c2[j] && index == -1) {
                    index = j;
                }
                if (c1[j] == c1[i] && c2[j] == c2[i]) {
                    c1[j] = c2[j] = '0';
                    times++;
                    isFindSame = true;
                    break;
                }
            }

            if (index != -1 && !isFindSame) {
                c1[index] = c2[index] = '0';
                times += 2;
            } else if (index == -1 && !isFindSame) {
                return -1;
            }
        }

        return times;
    }

    /**
     * 优化方案，分别统计x-y=a、y-x=b的个数，
     * 若（a+b） % 2 = 1，则不能通过交换使其相同
     * 若为偶数，则a中能通过a/2次交换使得至少a-1个相同，b中能通过b/2次交换使得至少b-1个相同
     * 剩余的通过a或者b的奇偶来判断是否需要最后一次2次的交换（xy, yx的情况）
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 交换的次数
     */
    private static int minimumSwap2(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int len = c1.length;
        int x = 0;
        int y = 0;
        for (int i = 0; i < len; i++) {
            if (c1[i] != c2[i]) {
                int temp = c1[i] == 'x' ? x++ : y++;
            }
        }

        if ((x + y) % 2 != 0) {
            return -1;
        } else {
            return x / 2 + y / 2 + (x % 2) * 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(minimumSwap2("xy", "yx"));
    }
}

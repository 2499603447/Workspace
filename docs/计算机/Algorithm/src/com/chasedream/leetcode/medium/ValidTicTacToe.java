package com.chasedream.leetcode.medium;

public class ValidTicTacToe {

    public static void main(String[] args) {
        //String strings[] = new String[]{"O  ", "   ", "   "};
        //String strings[] = new String[]{"XOX", " X ", "   "};
        //String strings[] = new String[]{"XXX", "   ", "OOO"};
        String strings[] = new String[]{"XOX", "O O", "XOX"};
        System.out.println(validTicTacToe(strings));
    }

    private static int[][] success =
            {
                    {0, 1, 2},
                    {0, 4, 8},
                    {0, 3, 6},
                    {1, 4, 7},
                    {2, 4, 6},
                    {2, 5, 8},
                    {3, 4, 5},
                    {6, 7, 8}};

    public static boolean validTicTacToe(String[] board) {
        int len = board.length;
        boolean[] x = new boolean[9];
        boolean[] o = new boolean[9];
        int xNum = 0;
        int oNum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'X') {
                    x[j + 3 * i] = true;
                    xNum++;
                } else if (board[i].charAt(j) == 'O') {
                    o[j + 3 * i] = true;
                    oNum++;
                }
            }
        }

        if (xNum == oNum && !isSuccess(x) || xNum == oNum + 1 && !isSuccess(o)) {
            return true;
        }


        return false;
    }

    private static boolean isSuccess(boolean[] b) {
        for (int i = 0; i < 8; i++) {

            if (b[success[i][0]] && b[success[i][1]] && b[success[i][2]]) {
                return true;
            }
        }
        return false;
    }
}

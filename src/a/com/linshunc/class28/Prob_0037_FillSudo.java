package a.com.linshunc.class28;

import java.util.Arrays;

public class Prob_0037_FillSudo {

    public static void main(String[] args) {
        char[][] board = {
                {'1', '.', '.', '8', '3', '.', '.', '.', '2'},
                {'5', '7', '.', '.', '.', '1', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '9', '.', '6', '4'},
                {'7', '.', '4', '.', '.', '8', '5', '9', '.'},
                {'.', '.', '3', '.', '1', '.', '4', '.', '.'},
                {'.', '5', '1', '4', '.', '.', '3', '.', '6'},
                {'3', '6', '.', '7', '.', '4', '.', '.', '.'},
                {'.', '.', '.', '6', '.', '.', '.', '7', '9'},
                {'8', '.', '.', '.', '5', '2', '.', '.', '3'},
        };
        solveSudoku(board);
    }

    public static void solveSudoku(char[][] board) {
        boolean[][] x = new boolean[9][10];
        boolean[][] y = new boolean[9][10];
        boolean[][] boo = new boolean[9][10];
        init(board, x, y, boo);

        process(board, x, y, boo, 0, 0);
    }

    public static boolean process(char[][] board, boolean[][] x, boolean[][] y, boolean[][] boo, int i, int j) {
        if (i == 9) {
            printArr(board);
            return true;
        }
        int nextI = j == 8 ? i + 1 : i;
        int nextJ = j == 8 ? 0 : j + 1;
        if (board[i][j] != '.') {
            return process(board, x, y, boo, nextI, nextJ);
        }
        else {
            int booIndex = (i / 3) * 3 + (j / 3);
            // 填入的数字
            for (int k = 1; k < 10; k++) {
                if (x[i][k] || y[j][k] || boo[booIndex][k]) {
                    continue;
                }
                board[i][j] = (char) ('0' + k);
                x[i][k] = true;
                y[j][k] = true;
                boo[booIndex][k] = true;
                if (process(board, x, y, boo, nextI, nextJ)) {
                    return true;
                }
                x[i][k] = false;
                y[j][k] = false;
                boo[booIndex][k] = false;
                board[i][j] = '.';
            }
        }
        return false;
    }

    private static void printArr(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            char[] chars = board[i];
            System.out.println(Arrays.toString(chars));
        }
        System.out.println();
    }

    public static void init(char[][] board, boolean[][] x, boolean[][] y, boolean[][] boo){
        // i行j列
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int booIndex = (i / 3) * 3 + (j / 3);
                char curNum = board[i][j];
                if (curNum == '.') {
                    continue;
                }
                int curIndex = curNum - '0';

                x[i][curIndex] = true;
                y[j][curIndex] = true;
                boo[booIndex][curIndex] = true;
            }
        }
        return;
    }

}

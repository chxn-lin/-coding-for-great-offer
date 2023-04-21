package a.com.linshunc.class31;

import java.util.Arrays;

public class Prob_0130_SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'O', 'X', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'X', 'X'},
                {'X', 'X', 'O', 'X', 'X'},
        };
        solve1(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public static void solve1(char[][] board) {
        if (board == null || board.length < 1) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                process(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') {
                process(board, i, board[0].length - 1);
            }
        }
        for (int i = 1; i < board[0].length - 2; i++) {
            if (board[0][i] == 'O') {
                process(board, 0, i);
            }
            if (board[board.length - 1][i] == 'O') {
                process(board, board.length - 1, i);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // 感染成Y
    private static void process(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1
                || j < 0 || j > board[0].length - 1
                || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'Y';
        process(board, i + 1, j);
        process(board, i - 1, j);
        process(board, i, j + 1);
        process(board, i, j - 1);
    }

}

package a.com.linshunc.class34;

import class34.Problem_0289_GameOfLife;

import java.util.Arrays;

public class Prob_0289_GameOfLife {

    /**
     * 生命游戏，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
     * 每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
     * Leetcode题目 : https://leetcode.com/problems/game-of-life/
     */
    public static void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int count = getLifeCount(board, i, j);
                if (2 <= count && count <= 3) {
                    if (2 == count) {
                        if (board[i][j] == 1) {
                            board[i][j] |= (1 << 1);
                        }
                    }
                    else {
                        board[i][j] |= (1 << 1);
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    private static int getLifeCount(int[][] board, int i, int j) {
        return f(board, i + 1, j)
                + f(board, i - 1, j)
                + f(board, i, j + 1)
                + f(board, i, j - 1)
                + f(board, i + 1, j + 1)
                + f(board, i + 1, j - 1)
                + f(board, i - 1, j + 1)
                + f(board, i - 1, j - 1);
    }

    private static int f(int[][] board, int i, int i1) {
        return (i < board.length && i >= 0 && i1 >= 0 && i1 < board[i].length && ((board[i][i1] & 1) == 1)) ? 1 : 0;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0}
        };
        int[][] board2 = {
                {0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0}
        };
        gameOfLife(board);
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
        Problem_0289_GameOfLife.gameOfLife(board2);
        for (int[] ints : board2) {
            System.out.println(Arrays.toString(ints));
        }
    }

}

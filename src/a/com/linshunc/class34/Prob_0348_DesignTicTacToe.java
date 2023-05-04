package a.com.linshunc.class34;

public class Prob_0348_DesignTicTacToe {

    class TicTacToe {
        int[][] rows;
        int[][] cols;
        boolean[][] mar;
        int[] left;
        int[] right;
        int len;

        public TicTacToe(int n) {
            rows = new int[n][3];
            cols = new int[n][3];
            mar = new boolean[n][n];
            left = new int[3];
            right = new int[3];
            this.len = n;
        }

        public int move(int row, int col, int player) {
            if (mar[row][col]) {
                return 0;
            }
            mar[row][col] = true;
            rows[row][player]++;
            cols[col][player]++;
            if (row == col) {
                left[player]++;
            }
            if (row + col == len) {
                right[player]++;
            }
            if (rows[row][player] == len || cols[row][player] == len
                    || left[player] == len || right[player] == len) {
                return player;
            }
            return 0;
        }

    }
}

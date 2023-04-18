package a.com.linshunc.class28;

public class Prob_0036_ValidSudo {

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
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean[][] x = new boolean[9][10];
        boolean[][] y = new boolean[9][10];
        boolean[][] boo = new boolean[9][10];
        // i行j列
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int booIndex = (i / 3) * 3 + (j / 3);
                char curNum = board[i][j];
                if (curNum == '.') {
                    continue;
                }
                int curIndex = curNum - '0';
                if (x[i][curIndex] || y[j][curIndex] || boo[booIndex][curIndex]) {
                    return false;
                }
                else {
                    x[i][curIndex] = true;
                    y[j][curIndex] = true;
                    boo[booIndex][curIndex] = true;
                }
            }
        }
        return true;
    }

}

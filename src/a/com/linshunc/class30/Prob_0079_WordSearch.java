package a.com.linshunc.class30;

public class Prob_0079_WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'a','b','c','d'},
                {'e','f','g','h'},
                {'i','j','k','l'},
                {'m','n','o','p'},
        };
        String word = "cdhi";
        System.out.println(exist(board, word));
        System.out.println(exist2(board, word));
    }

    public static boolean exist2(char[][] board, String word) {
        if (word.length() < 1) {
            return true;
        }
        if (board.length < 1) {
            return false;
        }
        char[] words = word.toCharArray();

        boolean[][][] dp = new boolean[board.length + 1][board[0].length + 1][words.length + 1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dp[i][j][words.length] = true;
            }
        }
        for (int k = words.length - 1; k >= 0; k--) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {

                    if (board[i][j] != words[k]) {
                        dp[i][j][k] = false;
                    }
                    else {
                        boolean res = false;
                        if (i > 1) {
                            res |= dp[i - 1][j][k + 1];
                        }
                        if (j > 1) {
                            res |= dp[i][j - 1][k + 1];;
                        }
                        res |= dp[i + 1][j][k + 1]
                                || dp[i][j + 1][k + 1];
                        dp[i][j][k] = res;
                    }

                }
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dp[i][j][0]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean exist(char[][] board, String word) {
        if (word.length() < 1) {
            return true;
        }
        if (board.length < 1) {
            return false;
        }
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean process(char[][] board, char[] word, int i, int j, int k) {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return false;
        }
        if (board[i][j] != word[k]) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = 0;
        boolean res = process(board, word, i + 1, j, k + 1)
                || process(board, word, i - 1, j, k + 1)
                || process(board, word, i, j + 1, k + 1)
                || process(board, word, i, j - 1, k + 1);
        board[i][j] = temp;
        return res;
    }


}

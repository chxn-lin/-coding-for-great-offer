package a.com.linshunc.class08;

import java.util.Arrays;

import static class08.Code04_SnakeGame.generateRandomArray;

public class Code04_Snake {

    public static int walk1(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Info pre = process(matrix, i, j);
                res = Math.max(res, Math.max(pre.no, pre.yes));
            }
        }

        return res;
    }

    private static class Info {
        int yes;
        int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    // 假设，值为 -1 代表没办法走到
    private static Info process(int[][] matrix, int i, int j) {
        if (j == 0) {
            return new Info(Math.max(-matrix[i][j], -1), Math.max(matrix[i][j], -1));
        }
        Info left = process(matrix, i, j - 1);
        Info leftUp = i > 0 ? process(matrix, i - 1, j - 1) : null;
        Info leftDown = i < matrix.length - 1 ? process(matrix, i + 1, j - 1) : null;

        int no = left.no;
        int yes = -1;
        if (left.no != -1) {
            yes = Math.max(yes, left.no + (-matrix[i][j]));
        }
        if (left.yes != -1) {
            yes = Math.max(yes, left.yes + matrix[i][j]);
        }

        if (leftUp != null) {
            no = Math.max(no, leftUp.no);
            if (leftUp.no != -1) {
                yes = Math.max(yes, leftUp.no + (-matrix[i][j]));
            }
            if (leftUp.yes != -1) {
                yes = Math.max(yes, leftUp.yes + matrix[i][j]);
            }
        }
        if (leftDown != null) {
            no = Math.max(no, leftDown.no);
            if (leftDown.no != -1) {
                yes = Math.max(yes, leftDown.no + (-matrix[i][j]));
            }
            if (leftDown.yes != -1) {
                yes = Math.max(yes, leftDown.yes + matrix[i][j]);
            }
        }
        no = no != -1 ? Math.max(no + matrix[i][j], -1) : -1;
        return new Info(yes, no);
    }

    public static int walk2(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int res = 0;
        // i j 2
        int[][][] dp = new int[matrix.length][matrix[0].length][2];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0][0] = Math.max(-matrix[i][0], -1);
            dp[i][0][1] = Math.max(matrix[i][0], -1);

            res = Math.max(res, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < dp[0].length; j++) {
            for (int i = 0; i < dp.length; i++) {
                int[] left = dp[i][j - 1];
                int[] leftUp = i > 0 ? dp[i - 1][j - 1] : null;
                int[] leftDown = i < matrix.length - 1 ? dp[i + 1][j - 1] : null;

                int no = left[1];
                int yes = -1;
                if (left[1] != -1) {
                    yes = Math.max(yes, left[1] + (-matrix[i][j]));
                }
                if (left[0] != -1) {
                    yes = Math.max(yes, left[0] + matrix[i][j]);
                }

                if (leftUp != null) {
                    no = Math.max(no, leftUp[1]);
                    if (leftUp[1] != -1) {
                        yes = Math.max(yes, leftUp[1] + (-matrix[i][j]));
                    }
                    if (leftUp[0] != -1) {
                        yes = Math.max(yes, leftUp[0] + matrix[i][j]);
                    }
                }
                if (leftDown != null) {
                    no = Math.max(no, leftDown[1]);
                    if (leftDown[1] != -1) {
                        yes = Math.max(yes, leftDown[1] + (-matrix[i][j]));
                    }
                    if (leftDown[0] != -1) {
                        yes = Math.max(yes, leftDown[0] + matrix[i][j]);
                    }
                }
                no = no != -1 ? Math.max(no + matrix[i][j], -1) : -1;

                dp[i][j][0] = yes;
                dp[i][j][1] = no;

                res = Math.max(res, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }

        return res;
    }

    public static void main(String[] args) {
//        int[][] arr = {
//                {-1, 2, 1},
//                {-1, 4, -8},
//        };
//        System.out.println(class08.Code04_SnakeGame.walk2(arr));
//        System.out.println(walk2(arr));
//        [-1, 2, 1]
//        [-1, 4, -8]

        int N = 7;
        int M = 7;
        int V = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * (N + 1));
            int c = (int) (Math.random() * (M + 1));
            int[][] matrix = generateRandomArray(r, c, V);
            int ans1 = walk2(matrix);
            int ans2 = class08.Code04_SnakeGame.walk2(matrix);
            if (ans1 != ans2) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("finish");
    }

}

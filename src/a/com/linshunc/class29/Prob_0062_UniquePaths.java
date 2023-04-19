package a.com.linshunc.class29;

import class29.Problem_0062_UniquePaths;

public class Prob_0062_UniquePaths {

    public static void main(String[] args) {
        int max = 15;
        int times = 1000_0000;
        for (int i = 0; i < times; i++) {
            int m = (int)(Math.random() * max) + 1;
            int n = (int)(Math.random() * max) + 1;

            int i1 = uniquePaths3(m, n);
            int i2 = Problem_0062_UniquePaths.uniquePaths(m, n);
            if (i1 != i2) {
                System.out.println("Oops");
                System.out.println(m);
                System.out.println(n);
                System.out.println(i1);
                System.out.println(i2);
                return;
            }

        }
        System.out.println("Success");

//        int m = 14;
//        int n = 2;
//        System.out.println(uniquePaths3(m, n));
//        System.out.println(Problem_0062_UniquePaths.uniquePaths(m, n));
    }

    // 排列组合
    public static int uniquePaths3(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int all = m + n - 2;
        int sub = m - 1;

        return c(all, sub);
    }

    private static int c(int all, int sub) {
        double fenmu = 1;
        double fenzi = 1;
        int startNum = all - sub + 1;
        for (int i = startNum, k = 1; i <= all; i++, k++) {
            fenmu *= i;
            fenzi *= k;
            double fc = fc(fenmu, fenzi);
            fenmu /= fc;
            fenzi /= fc;
        }

        return (int) (fenmu/fenzi);
    }

    private static double fc(double fenmu, double fenzi) {
        return fenzi == 0 ? fenmu : fc(fenzi, fenmu % fenzi);
    }


    public static int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        return process(m - 1, n - 1, 0, 0);
    }

    public static int uniquePaths2(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int p1 = 0;
                int p2 = 0;
                if (i < m - 1) {
                    p1 = dp[i + 1][j];
                }
                if (j < n - 1) {
                    p2 = dp[i][j + 1];
                }
                dp[i][j] = p1 + p2;
            }
        }

        return dp[0][0];
    }

    private static int process(int m, int n, int xIndex, int yIndex) {
        if (xIndex == m && yIndex == n) {
            return 1;
        }
        else {
            int p1 = 0;
            int p2 = 0;
            if (xIndex < m) {
                p1 = process(m, n, xIndex + 1, yIndex);
            }
            if (yIndex < n) {
                p2 = process(m, n, xIndex, yIndex + 1);
            }
            return p1 + p2;
        }
    }

}

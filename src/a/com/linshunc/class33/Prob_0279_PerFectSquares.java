package a.com.linshunc.class33;

import class33.Problem_0279_PerfectSquares;

public class Prob_0279_PerFectSquares {

    // 暴力解
    public static int numSquares1(int n) {
        if (n <= 1) {
            return 1;
        }
        else if (n < 3) {
            return 2;
        }
        else if (n < 4) {
            return 3;
        }
        else if (n == 4) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 1;
        for (int i = 5; i <= n; i++) {
            int sub = (int) Math.sqrt(i);
            if (sub * sub == i) {
                dp[i] = 1;
                continue;
            }
            int res = i;
            for (int j = sub; j > 0; j--) {
                res = Math.min(dp[i - j * j] + 1, res);
            }
            dp[i] = res;
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(i + ":" + dp[i]);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int i = 1000;
        int p1 = numSquares1(i);
        int p2 = Problem_0279_PerfectSquares.numSquares1(i);
        System.out.println(p1);
        System.out.println(p2);

//        for (int i = 0; i < 1_000; i++) {
//            int p1 = numSquares1(i);
//            int p2 = Problem_0279_PerfectSquares.numSquares3(i);
//            if (p1 != p2) {
//                System.out.println("Oops!");
//                System.out.println(i);
//                System.out.println(p1);
//                System.out.println(p2);
//                return;
//            }
//        }
//        System.out.println("Success");
    }

}

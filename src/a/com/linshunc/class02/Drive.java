package a.com.linshunc.class02;

import static class02.Code04_Drive.maxMoney3;
import static class02.Code04_Drive.randomMatrix;

public class Drive {

    public static int method1(int[][] income){
        if (income == null || income.length == 0 || (income.length & 1) == 1) {
            return 0;
        }
        return process1(income, 0, income.length / 2);
    }

    // rest表示去A还剩下多少
    private static int process1(int[][] income, int index, int rest) {
        if (index  == income.length) {
            return 0;
        }
        int res = 0;
        if (rest == 0) {
            res = income[index][1] + process1(income, index + 1, rest);
        } else if (income.length == index + rest) {
            res = income[index][0] + process1(income, index + 1, rest - 1);
        } else {
            int p1 = income[index][1] + process1(income, index + 1, rest);
            int p2 = income[index][0] + process1(income, index + 1, rest - 1);
            res = Math.max(p1, p2);
        }
        return res;
    }

    public static int method2(int[][] income){
        if (income == null || income.length == 0 || (income.length & 1) == 1) {
            return 0;
        }
        int[][] dp = new int[income.length + 1][income.length / 2 + 1];

        for (int index = income.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= income.length / 2; rest++) {
                int res = 0;
                if (rest == 0) {
                    res = income[index][1] + process1(income, index + 1, rest);
                } else if (income.length == index + rest) {
                    res = income[index][0] + process1(income, index + 1, rest - 1);
                } else {
                    int p1 = income[index][1] + process1(income, index + 1, rest);
                    int p2 = income[index][0] + process1(income, index + 1, rest - 1);
                    res = Math.max(p1, p2);
                }
                dp[index][rest] = res;
            }
        }

        return dp[0][income.length / 2];
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans2 = method2(matrix);
            int ans3 = maxMoney3(matrix);
            if (ans2 != ans3) {
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}

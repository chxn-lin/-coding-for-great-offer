package a.com.linshunc.class10;

public class Code03_MyKInversePairs {

    public static int kInversePairs(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        int mod = 1000000007;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                if (j >= i) {
                    dp[i][j] = (dp[i][j] + mod - dp[i - 1][j - i]) % mod;
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 1;
        System.out.println(kInversePairs(n, k));
    }
}

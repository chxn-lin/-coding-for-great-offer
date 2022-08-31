package a.com.linshunc.class04;

public class CheckIsJiaocuo {

    public static boolean method1(String s1, String s2, String s3){
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        char[] arr3 = s3.toCharArray();
        boolean[][] dp = new boolean[arr1.length + 1][arr2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= arr1.length; i++) {
            if (arr1[i] == arr3[i]) {
                dp[i][0] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= arr2.length; i++) {
            if (arr2[i] == arr3[i]) {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length; j++) {
                boolean p1 = arr1[i - 1] == arr3[i + j - 1] && dp[i - 1][j];
                boolean p2 = arr2[j - 1] == arr3[i + j - 1] && dp[i][j - 1];
                dp[i][j] = p1 || p2;
            }
        }
        return dp[arr1.length][arr2.length];
    }

    public static boolean method2(String s1, String s2, String s3){
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        return process(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), s1.toCharArray().length, s2.toCharArray().length);
    }

    private static boolean process(char[] arr1, char[] arr2, char[] arr3, int i, int j) {
        if (i == 0 && j == 0) {
            return true;
        }
        if (i == 0) {
            return arr2[j] == arr3[j] && process(arr1, arr2, arr3, 0, j - 1);
        }
        if (j == 0) {
            return arr1[i] == arr3[i] && process(arr1, arr2, arr3, i - 1, 0);
        }
        boolean p1 = arr1[i - 1] == arr3[i + j - 1] && process(arr1, arr2, arr3, i - 1, j);
        boolean p2 = arr2[j - 1] == arr3[i + j - 1] && process(arr1, arr2, arr3, i, j - 1);

        return p1 || p2;
    }

    public static void main(String[] args) {
        String s1 = "aaabcd";
        String s2 = "adccb";
        String s3 = "aaaadbcccdb";

        System.out.println(method2(s1, s2, s3));
    }

}

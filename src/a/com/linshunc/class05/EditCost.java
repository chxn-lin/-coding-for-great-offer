package a.com.linshunc.class05;

import static class05.Code03_EditCost.minCost2;

public class EditCost {

    public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i * dc;
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i * ic;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 最后一个是一样的
                int p1 = dp[i - 1][j - 1] + dc + ic;
                int p2 = dp[i - 1][j - 1] + rc;
                int p3 = Integer.MAX_VALUE;
                if (arr1[i - 1] == arr2[j - 1]) {
                    p3 = dp[i - 1][j - 1];
                }
                int p4 = dp[i - 1][j] + dc;
                int p5 = dp[i][j - 1] + ic;
                dp[i][j] = Math.min(Math.min(Math.min(p1, p2), Math.min(p3, p4)), p5);
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 3, 2, 4));
        System.out.println(minCost2(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 1, 7, 5));
        System.out.println(minCost2(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost1(str1, str2, 2, 9, 8));
        System.out.println(minCost2(str1, str2, 2, 9, 8));

    }

}

package a.com.linshunc.class11;

import class11.Code01_MinimumInsertionStepsToMakeAStringPalindrome;

import java.util.ArrayList;
import java.util.List;

public class Class01_ReturnStr {

    public static int minInsertions(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                int cur = arr.length;
                if (arr[i] == arr[j]) {
                    cur = dp[i + 1][j - 1];
                }
                else {
                    cur = Math.min(dp[i + 1][j] + 1, dp[i][j - 1] + 1);
                }
                dp[i][j] = cur;
            }
        }
        return dp[0][s.length() - 1];
    }

    public static String minInsertionsOneWay(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] arr = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                int cur = arr.length;
                if (arr[i] == arr[j]) {
                    cur = dp[i + 1][j - 1];
                }
                else {
                    cur = Math.min(dp[i + 1][j] + 1, dp[i][j - 1] + 1);
                }
                dp[i][j] = cur;
            }
        }
        int resL = 0;
        int resR = dp[0][s.length() - 1] + s.length() - 1;
        char[] res = new char[resR + 1];

        int curL = 0;
        int curR = s.length() - 1;
        while (curL < curR) {
            if (dp[curL][curR] == dp[curL + 1][curR] + 1) {
                res[resL++] = arr[curL];
                res[resR--] = arr[curL];
                curL++;
            }
            else if (dp[curL][curR] == dp[curL][curR - 1] + 1) {
                res[resL++] = arr[curR];
                res[resR--] = arr[curR];
                curR--;
            }
            else if (dp[curL][curR] == dp[curL + 1][curR - 1]) {
                res[resL++] = arr[curL];
                res[resR--] = arr[curL];
                curL++;
                curR--;
            }
        }
        if (curL == curR) {
            res[resL] = arr[curL];
        }

        return String.valueOf(res);
    }

    public static List<String> minInsertionsAllWays(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
            return ans;
        }
        char[] arr = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                int cur = arr.length;
                if (arr[i] == arr[j]) {
                    cur = dp[i + 1][j - 1];
                }
                else {
                    cur = Math.min(dp[i + 1][j] + 1, dp[i][j - 1] + 1);
                }
                dp[i][j] = cur;
            }
        }
        int len = dp[0][s.length() - 1] + s.length() - 1;
        char[] res = new char[len + 1];
        process(dp, arr, ans, res, 0, res.length - 1, 0, arr.length - 1);

        return ans;
    }

    private static void process(int[][] dp, char[] arr, List<String> ans,
                                char[] res,
                                int resL, int resR,
                                int curL, int curR) {
        if (curL >= curR) {
            if (curL == curR) {
                res[resL] = arr[curL];
            }
            ans.add(String.valueOf(res));
        }
        else {
            if (dp[curL][curR] == dp[curL + 1][curR] + 1) {
                res[resL] = arr[curL];
                res[resR] = arr[curL];
                process(dp, arr, ans, res, resL + 1, resR - 1, curL + 1, curR);
            }
            if (dp[curL][curR] == dp[curL][curR - 1] + 1) {
                res[resL] = arr[curR];
                res[resR] = arr[curR];
                process(dp, arr, ans, res, resL + 1, resR - 1, curL, curR - 1);
            }
            if (dp[curL][curR] == dp[curL + 1][curR - 1]) {
                res[resL] = arr[curL];
                res[resR] = arr[curL];
                process(dp, arr, ans, res, resL + 1, resR - 1, curL + 1, curR - 1);
            }
        }
    }

    public static void main(String[] args) {
//        String s = "abcc";
//        List<String> list = minInsertionsAllWays(s);
//        System.out.println(list);

        String s = null;
        String ans2 = null;
        List<String> ans3 = null;

        System.out.println("本题第二问，返回其中一种结果测试开始");
        s = "mbadm";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "leetcode";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "aabaa";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);
        System.out.println("本题第二问，返回其中一种结果测试结束");

        System.out.println();

        System.out.println("本题第三问，返回所有可能的结果测试开始");
        s = "mbadm";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "leetcode";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "aabaa";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();
        System.out.println("本题第三问，返回所有可能的结果测试结束");
    }

}

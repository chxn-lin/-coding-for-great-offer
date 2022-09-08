package a.com.linshunc.class05;

import class05.Code04_DeleteMinCost;

import java.util.ArrayList;
import java.util.List;

import static class05.Code04_DeleteMinCost.generateRandomString;

public class DeleteMinCost {

    public static int minCost1(String s1, String s2) {
        List<String> list = new ArrayList<>();
        process(s2.toCharArray(), list, 0, "");
        list.sort((o1, o2) -> o2.length() - o1.length());
        int res = s2.length();
        for (String s : list) {
            if (s1.indexOf(s) != -1) {
                res = s2.length() - s.length();
                break;
            }
        }
        return res;
    }

    public static int minCost2(String s1, String s2){
        char[] arr2 = s2.toCharArray();
        int res = s2.length();
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start + 1; end <= s1.length(); end++) {
                res = Math.min(res, onlyDelete(arr2, s1.substring(start, end).toCharArray()));
            }
        }
        return res;
    }

    public static int onlyDelete(char[] x, char[] y) {
        if (x.length < y.length) {
            return Integer.MAX_VALUE;
        }
        int N = x.length;
        int M = y.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        // dp[i][j]表示前缀长度
        for (int i = 1; i <= N; i++) {
            dp[i][0] = i;
        }
        for (int xlen = 1; xlen <= N; xlen++) {
            for (int ylen = 1; ylen <= Math.min(M, xlen); ylen++) {
                if (dp[xlen - 1][ylen] != Integer.MAX_VALUE) {
                    dp[xlen][ylen] = dp[xlen - 1][ylen] + 1;
                }
                if (x[xlen - 1] == y[ylen - 1] && dp[xlen - 1][ylen - 1] != Integer.MAX_VALUE) {
                    dp[xlen][ylen] = Math.min(dp[xlen][ylen], dp[xlen - 1][ylen - 1]);
                }
            }
        }
        return dp[N][M];
    }

    private static void process(char[] arr, List<String> list, int index, String pre) {
        if (index == arr.length) {
            list.add(pre);
            return;
        }
        process(arr, list, index + 1, pre + arr[index]);
        process(arr, list, index + 1, pre);
    }

    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "ac";

        System.out.println(minCost1(str1, str2));
        System.out.println(Code04_DeleteMinCost.minCost3(str1, str2));

//        int str1Len = 20;
//        int str2Len = 10;
//        int v = 5;
//        int testTime = 10000;
//        boolean pass = true;
//        System.out.println("test begin");
//        for (int i = 0; i < testTime; i++) {
//            String str1 = generateRandomString(str1Len, v);
//            String str2 = generateRandomString(str2Len, v);
//            int ans1 = Code04_DeleteMinCost.minCost3(str1, str2);
//            int ans2 = minCost2(str1, str2);
//            if (ans1 != ans2) {
//                pass = false;
//                System.out.println(str1);
//                System.out.println(str2);
//                System.out.println(ans1);
//                System.out.println(ans2);
//                break;
//            }
//        }
//        System.out.println("test pass : " + pass);
    }

}

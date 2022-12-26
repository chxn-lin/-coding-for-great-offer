package a.com.linshunc.class11;

import class11.Code02_PalindromePartitioningII;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Class02_SplitRetStr {

    public static int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] arr = s.toCharArray();
        boolean[][] checkDp = getIsRetDp(arr);
        int[] dp = new int[arr.length];

        for (int i = dp.length - 2; i >= 0; i--) {
            if (checkDp[i][dp.length - 2]) {
                dp[i] = 1;
            } else {
                int res = Integer.MAX_VALUE;
                for (int j = i; j < dp.length; j++) {
                    if (checkDp[i][j]) {
                        res = Math.min(res, dp[j + 1]);
                    }
                }
                dp[i] = res + 1;
            }
        }

        return dp[0];
    }

    private static boolean[][] getIsRetDp(char[] arr) {
        boolean[][] dp = new boolean[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = true;
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            dp[i][i + 1] = arr[i] == arr[i + 1];
            for (int j = i + 2; j < arr.length; j++) {
                dp[i][j] = arr[i] == arr[j] && dp[i + 1][j - 1];
            }
        }
        return dp;
    }

    public static List<String> minCutOneWay(String s) {
        if (s == null || s.length() < 1) {
            return new ArrayList<>();
        }
        if (s.length() == 1) {
            List<String> list = new ArrayList<>();
            list.add(s);
            return list;
        }
        char[] arr = s.toCharArray();
        boolean[][] checkDp = getIsRetDp(arr);
        int[] dp = new int[arr.length];

        for (int i = dp.length - 1; i >= 0; i--) {
            if (checkDp[i][dp.length - 1]) {
                dp[i] = 1;
            } else {
                int res = Integer.MAX_VALUE;
                for (int j = i; j < dp.length; j++) {
                    if (checkDp[i][j]) {
                        res = Math.min(res, dp[j + 1]);
                    }
                }
                dp[i] = res + 1;
            }
        }
        List<String> list = new ArrayList<>();

        int curEndIndex = 0;
        int curSplit = dp[0];
        while (curSplit > 1) {
            for (int i = curEndIndex + 1; i <= dp.length - 1; i++) {
                if (curSplit - 1 == dp[i] && checkDp[curEndIndex][i - 1]) {
                    list.add(s.substring(curEndIndex, i));
                    curEndIndex = i;
                    curSplit--;
                }
            }
        }
        list.add(s.substring(curEndIndex));

        return list;
    }

    public static List<List<String>> minCutAllWays(String s) {
        if (s == null || s.length() < 1) {
            return new ArrayList<>();
        }
        if (s.length() == 1) {
            List<String> list = new ArrayList<>();
            List<List<String>> res = new ArrayList<>();
            list.add(s);
            res.add(list);
            return res;
        }
        char[] arr = s.toCharArray();
        boolean[][] checkDp = getIsRetDp(arr);
        int[] dp = new int[arr.length];

        for (int i = dp.length - 1; i >= 0; i--) {
            if (checkDp[i][dp.length - 1]) {
                dp[i] = 1;
            } else {
                int res = Integer.MAX_VALUE;
                for (int j = i; j < dp.length; j++) {
                    if (checkDp[i][j]) {
                        res = Math.min(res, dp[j + 1]);
                    }
                }
                dp[i] = res + 1;
            }
        }
        List<List<String>> res = new ArrayList<>();
        process(s, dp, checkDp, 0, dp[0], res, new ArrayList<>());
        return res;
    }

    private static void process(String s,
                                int[] dp,
                                boolean[][] checkDp,
                                int curIndex,
                                int curSplit,
                                List<List<String>> res,
                                List<String> curList) {
       if (curSplit == 1) {
           curList.add(s.substring(curIndex));
           List<String> list = new ArrayList<>(curList.size());
           copy(curList, list);
           res.add(list);
           curList.remove(curList.size() - 1);
       }
       else {
           for (int i = curIndex + 1; i <= dp.length - 1; i++) {
               if (curSplit - 1 == dp[i] && checkDp[curIndex][i - 1]) {
                   curList.add(s.substring(curIndex, i));
                   process(s, dp, checkDp, i, curSplit - 1, res, curList);
                   curList.remove(curList.size() - 1);
               }
           }
       }
    }

    private static void copy(List<String> source, List<String> target){
        for (String s : source) {
            target.add(s);
        }
    }

    public static void main(String[] args) {
//        String str = "ssassssssass";
        String str = "adhghahdjhshdhdsssd";
        System.out.println(minCutOneWay(str));
        System.out.println(Code02_PalindromePartitioningII.minCutOneWay(str));

        System.out.println(minCutAllWays(str));
        System.out.println(Code02_PalindromePartitioningII.minCutAllWays(str));
    }

}

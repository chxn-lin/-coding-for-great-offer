package a.com.linshunc.class01;

import static class01.Code05_LongestIncreasingPath.longestIncreasingPath2;

public class LongestPath {

    public static int method1(int[][] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res = Math.max(res, process1(arr, i, j));
            }
        }
        return res;
    }

    private static int process1(int[][] arr, int i, int j) {
        int up = (i - 1 > 0 && arr[i - 1][j] > arr[i][j]) ? process1(arr, i - 1, j) : 0;
        int down = (i + 1 < arr.length && arr[i + 1][j] > arr[i][j]) ? process1(arr, i + 1, j) : 0;
        int left = (j - i > 0 && arr[i][j - 1] > arr[i][j]) ? process1(arr, i, j - 1) : 0;
        int right = (j + 1 < arr[0].length && arr[i][j + 1] > arr[i][j]) ? process1(arr, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    public static int method2(int[][] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        int[][] dp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res = Math.max(res, process2(arr, i, j, dp));
            }
        }
        return res;
    }

    private static int process2(int[][] arr, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int up = (i - 1 > 0 && arr[i - 1][j] > arr[i][j]) ? process2(arr, i - 1, j, dp) : 0;
        int down = (i + 1 < arr.length && arr[i + 1][j] > arr[i][j]) ? process2(arr, i + 1, j, dp) : 0;
        int left = (j - i > 0 && arr[i][j - 1] > arr[i][j]) ? process2(arr, i, j - 1, dp) : 0;
        int right = (j + 1 < arr[0].length && arr[i][j + 1] > arr[i][j]) ? process2(arr, i, j + 1, dp) : 0;
        int res = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1,2,3,4},
                {5,7,1,3},
                {4,8,9,1},
        };
        System.out.println(method2(arr));
        System.out.println(longestIncreasingPath2(arr));
    }

}

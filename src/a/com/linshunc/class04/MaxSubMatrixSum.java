package a.com.linshunc.class04;

import class04.Code03_SubMatrixMaxSum;

public class MaxSubMatrixSum {

    public static int method1(int[][] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            int[] pre = new int[arr[0].length];
            for (int j = i; j < arr.length; j++) {
                for (int k = 0; k < arr[0].length; k++) {
                    pre[k] += arr[j][k];
                }
                res = Math.max(res, getMaxSum(pre));
            }
        }

        return res;
    }

    public static int getMaxSum(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = 0;

        int lastMax = 0;
        for (int i = 0; i < arr.length; i++) {
            lastMax = lastMax > 0 ? lastMax + arr[i] : arr[i];
            res = Math.max(res, lastMax);
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1,3,3,-2,2},
                {1,-3,3,2,-5},
                {-1,3,-3,2,-5},
                {1,-3,3,-2,5},
        };
        System.out.println(method1(arr));
        System.out.println(Code03_SubMatrixMaxSum.maxSum(arr));
        System.out.println("");
    }

}

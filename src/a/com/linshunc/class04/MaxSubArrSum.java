package a.com.linshunc.class04;

import class04.Code02_SubArrayMaxSum;

import java.util.TreeSet;

public class MaxSubArrSum {

    // 子数组必须连续
    public static int method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] sumArr = new int[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }
        int res = Integer.MIN_VALUE;
        TreeSet<Integer> tree = new TreeSet();
        tree.add(0);
        // 以i结尾，最大的和为多少，维护一个局部内最大值的结构
        for (int i = 0; i < arr.length; i++) {
            res = Math.max(res, sumArr[i] - tree.first());
            tree.add(sumArr[i]);
        }
        return res;
    }

    public static int method2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = Integer.MIN_VALUE;

        int lastMax = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            lastMax = lastMax > 0 ? lastMax + arr[i] : arr[i];
            res = Math.max(res, lastMax);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {-1};
        System.out.println(method1(arr));
        System.out.println(method2(arr));
        System.out.println(Code02_SubArrayMaxSum.maxSubArray2(arr));
    }

}

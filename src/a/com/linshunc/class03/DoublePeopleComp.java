package a.com.linshunc.class03;

import java.util.Arrays;

import static class03.Code04_MaxPairNumber.*;

public class DoublePeopleComp {

    public static int method1(int[] arr, int k) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = 0;
        Arrays.sort(arr);
        boolean[] isUse = new boolean[arr.length];

        int L = 0;
        int R = 0;
        while (R != arr.length && L != arr.length) {
            if (isUse[L]) {
                L++;
                continue;
            }
            if (R == L || arr[R] - arr[L] < k) {
                R++;
            } else if (arr[R] - arr[L] == k) {
                isUse[R] = true;
                R++;
                L++;
                res++;
            } else {
                L++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {19,2,19,18,7,3,6,11,13};
//        int k = 4;
//        System.out.println(method1(arr, k));

        int maxLen = 10;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = method1(arr1, k);
            int ans2 = maxPairNum2(arr2, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}

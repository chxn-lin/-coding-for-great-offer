package a.com.linshunc.class03;

import class03.Code05_BoatsToSavePeople;

import java.util.Arrays;

import static class03.Code04_MaxPairNumber.copyArray;
import static class03.Code04_MaxPairNumber.printArray;

public class Boats2SavePeople {

    public static int method1(int[] arr, int limit) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        Arrays.sort(arr);
        if (arr[arr.length - 1] > limit) {
            return -1;
        }
        int res = 0;

        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            if (L == R) {
                L++;
                R--;
            } else {
                if (arr[L] + arr[R] <= limit) {
                    L++;
                    R--;
                } else {
                    R--;
                }
            }
            res++;
        }

        return res;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int maxK = 20;
        int testTime = 100000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1)) + 20;
            int ans1 = method1(arr1, k);
            int ans2 = Code05_BoatsToSavePeople.numRescueBoats2(arr2, k);
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

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value) + 1;
        }
        return arr;
    }

}

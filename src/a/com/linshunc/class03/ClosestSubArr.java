package a.com.linshunc.class03;

import class03.Code06_ClosestSubsequenceSum;

import java.util.Arrays;
import java.util.TreeSet;

import static a.com.linshunc.class03.Boats2SavePeople.randomArray;
import static class03.Code04_MaxPairNumber.copyArray;
import static class03.Code04_MaxPairNumber.printArray;

public class ClosestSubArr {

    public static int method1(int[] arr, int goal){
        if (arr == null || arr.length == 0) {
            return goal;
        }
        int res = Math.abs(goal);

        int[] L = new int[2 << 20];
        int[] R = new int[2 << 20];
        int lLimit = process(arr, 0, arr.length >> 1, 0, 0, L);
        int rLimit = process(arr, arr.length >> 1, arr.length, 0, 0, R);
        Arrays.sort(L, 0, lLimit);
        TreeSet<Integer> tree = new TreeSet<>();
        for (int i = 0; i < rLimit; i++) {
            tree.add(R[i]);
        }
        for (int i = 0; i < lLimit; i++) {
            int cur = goal - L[i];
            Integer floor = tree.floor(cur);
            floor = floor != null ? floor : 0;
            Integer ceiling = tree.ceiling(cur);
            ceiling = ceiling != null ? ceiling : 0;
            res = Math.min(res, Math.min(Math.abs(cur - floor), Math.abs(cur - ceiling)));
        }
        return res;
    }



    private static int process(int[] arr, int from, int to, int pre, int limit, int[] L) {
        if (from == to) {
            L[limit++] = pre;
        } else {
            limit = process(arr, from + 1, to, pre, limit, L);
            limit = process(arr, from + 1, to, pre + arr[from], limit, L);
        }
        return limit;
    }

    public static void main(String[] args) {
//        int[] arr = {20, 1, 6, 6};
//        int goal = 4;
//        System.out.println(method1(arr, goal));

        int maxLen = 40;
        int maxValue = 10000;
        int maxK = 10000;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = method1(arr1, k);
            int ans2 = Code06_ClosestSubsequenceSum.minAbsDifference(arr2, k);
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

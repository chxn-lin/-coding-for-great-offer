package a.com.linshunc.class09;

import class09.Code03_LIS;

import static a.com.linshunc.class01.AOE.printArr;
import static class07.Code01_MaxAndValue.randomArray;

public class Code03_MyLIS {

    public static int method1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        return process(arr, 0, Integer.MIN_VALUE);
    }

    private static int process(int[] arr, int index, int preMax) {
        if (index == arr.length) {
            return 0;
        }
        // 不选择
        int p1 = process(arr, index + 1, preMax);
        // 选择
        int p2 = 0;
        if (preMax < arr[index]) {
            p2 = process(arr, index + 1, arr[index]) + 1;
        }
        return Math.max(p1, p2);
    }

    public static int method2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        // 以 i 位置结尾，最长的递增子序列长度
        int[] dp = new int[arr.length];
        int maxLen = 1;
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            // 获取比 arr[i] 小的上一个位置的值
            int lastMaxLen = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    lastMaxLen = Math.max(dp[j], lastMaxLen);
                }
            }
            dp[i] = lastMaxLen + 1;
            maxLen = Math.max(dp[i], maxLen);
        }
        return maxLen;
    }

    public static int method3(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] end = new int[arr.length];
        int curEndIndex = 0;
        int L = 0;
        int R = 0;
        end[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            L = 0;
            R = curEndIndex;
            int lastSmallInd = -1;
            if (end[R] < arr[i]) {
                lastSmallInd = R;
            }
            else if (end[L] > arr[i]) {
                lastSmallInd = -1;
            }
            else {
                while (L <= R) {
                    int mid = L + ((R - L) >> 1);
                    if (end[mid] >= arr[i]) {
                        R = mid - 1;
                    } else {
                        L = mid + 1;
                        lastSmallInd = mid;
                    }
                }
            }
            // 最大，那么需要扩充
            if (lastSmallInd == curEndIndex) {
                end[++curEndIndex] = arr[i];
            } else {
                end[++lastSmallInd] = arr[i];
            }
        }
        return curEndIndex + 1;
    }

    public static void main(String[] args) {
//        int[] arr = {3, 9, 11, 4, 8, 9};
//        int ans1 = Code03_LIS.lengthOfLIS(arr);
//        int ans2 = method3(arr);
//            printArr(arr);
//            System.out.println(ans1);
//            System.out.println(ans2);

        int maxSize = 15;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[] arr = randomArray(size, range);
            int ans1 = Code03_LIS.lengthOfLIS(arr);
            int ans2 = method3(arr);
            if (ans1 != ans2) {
                printArr(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}

package a.com.linshunc.class10;

import class10.Code01_JumpGame;

import java.time.chrono.MinguoDate;

import static a.com.linshunc.class01.AOE.printArr;

public class Code01_Jump {

    public static int method1(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0);
    }

    private static int process(int[] arr, int index) {
        if (index >= arr.length - 1) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= arr[index]; i++) {
            int p1 = process(arr, index + i);
            ans = Math.min(p1, ans);
        }
        return ans + 1;
    }

    public static int method2(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int[] dp = new int[arr.length];

        dp[dp.length - 1] = 0;
        for (int index = dp.length - 2; index >= 0; index--) {
            int ans = Integer.MAX_VALUE;
            for (int i = 1; i <= Math.min(arr[index], dp.length - index - 1); i++) {
                int p1 = dp[index + i];
                ans = Math.min(p1, ans);
            }
            dp[index] = ans + 1;
        }

        return dp[0];
    }

    public static int method3(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int step = 1;
        int curMax = arr[0];
        int next = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (curMax < i) {
                step++;
                curMax = next;
            }
            next = Math.max(i + arr[i], next);

        }
        return step;
    }

    public static void main(String[] args) {
//        int nums = 100000;
//        int maxValue = 6;
//        int maxArrLen = 20;
//        System.out.println("=======测试开始======");
//        for (int i = 0; i < nums; i++) {
//            int[] arr = randomArr(maxArrLen, maxValue);
//            int ans1 = Code01_JumpGame.jump(arr);
//            int ans2 = method3(arr);
//            if (ans1 != ans2) {
//                printArr(arr);
//                System.out.println("true:" + ans1);
//                System.out.println("my  :" + ans2);
//                System.out.println("oops");
//            }
//        }
//        System.out.println("=======测试结束======");

        System.out.println(1 ^ 1);
        System.out.println(1 ^ 0);
        System.out.println(0 ^ 1);
        System.out.println(0 ^ 0);
    }

    private static int[] randomArr(int maxArrLen, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxArrLen)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * maxValue + 1);
        }
        return arr;
    }

}

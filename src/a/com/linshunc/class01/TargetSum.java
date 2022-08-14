package a.com.linshunc.class01;

import static a.com.linshunc.class01.AOE.printArr;
import static class01.Code07_TargetSum.findTargetSumWays;

public class TargetSum {

    public static int method1(int[] arr, int target){
        if (arr == null || arr.length < 1) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(arr[i], min);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] - min + 1;
        }
        return process1(arr, 0, target);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            if (rest == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int p1 = process1(arr, index + 1, rest + arr[index]);
        int p2 = process1(arr, index + 1, rest - arr[index]);
        return p1 + p2;
    }

    public static void main(String[] args) {
        int time = 100000;
        int maxLen = 10;
        int maxNum = 20;
        for (int i = 0; i < time; i++) {
            int[] arr = genArr(maxLen, maxNum);
            int target = (int) (Math.random() * maxNum);
            int p1 = method1(arr, target);
            int p2 = findTargetSumWays(arr, target);
            if (p1 != p2) {
                System.out.println("oops");
                printArr(arr);
                System.out.println(target);
            }
        }
    }

    private static int[] genArr(int maxLen, int maxNum) {
        int[] arr = new int[(int) (maxLen * Math.random()) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxNum / 2 - Math.random() * maxNum / 2);
        }
        return arr;
    }

}

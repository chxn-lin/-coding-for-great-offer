package a.com.linshunc.class07;

import static class07.Code04_Power2Diffs.*;

public class DifPowKindsNum {

    /*4、给定一个有序数组arr，其中值可能为正、负、0。
    返回arr中每个数都平方之后不同的结果有多少种？
    思路：其实就是比绝对值，左右指针
    谁大谁滑，一样一直滑到不相等的*/
    public static int diff1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = 0;
        int leftPoint = 0;
        int rightPoint = arr.length - 1;
        while (leftPoint <= rightPoint) {
            if (Math.pow(arr[leftPoint], 2) > Math.pow(arr[rightPoint], 2)) {
                leftPoint = getNextRightNum(arr, leftPoint);
            } else if (Math.pow(arr[leftPoint], 2) < Math.pow(arr[rightPoint], 2)) {
                rightPoint = getNextLeftNum(arr, rightPoint);
            } else {
                leftPoint = getNextRightNum(arr, leftPoint);
                rightPoint = getNextLeftNum(arr, rightPoint);
            }
            res++;
        }

        return res;
    }

    private static int getNextRightNum(int[] arr, int point) {
        int index = point + 1;
        while (index < arr.length && arr[index] == arr[point]) {
            index++;
        }
        return index;
    }

    private static int getNextLeftNum(int[] arr, int point) {
        int index = point - 1;
        while (index >= 0 && arr[index] == arr[point]) {
            index--;
        }
        return index;
    }

    public static void main(String[] args) {
//        int[] arr = {-5, -2};
//        int ans1 = diff1(arr);
//        int ans2 = diff2(arr);
//        System.out.println(ans1);
//        System.out.println(ans2);


        int len = 10;
        int value = 20;
        int testTimes = 200000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomSortedArray(len, value);
            int ans1 = diff1(arr);
            int ans2 = diff2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }

}

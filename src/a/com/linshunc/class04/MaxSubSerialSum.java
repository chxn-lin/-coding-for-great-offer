package a.com.linshunc.class04;

import class04.Code04_SubArrayMaxSumFollowUp;

import static a.com.linshunc.class01.AOE.printArr;
import static class04.Code01_QueryHobby.generateRandomArray;

public class MaxSubSerialSum {

    public static int method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = 0;

        int lastLastMax = arr[0];
        if (arr.length == 1) {
            return lastLastMax;
        }
        res = Math.max(arr[1], lastLastMax);
        int lastMax = res;

        for (int i = 2; i < arr.length; i++) {
            int p1 = arr[i] + lastLastMax;
            int p2 = lastMax;
            res = Math.max(p1, p2);
            lastLastMax = lastMax;
            lastMax = res;
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {10,8};
//        System.out.println(method1(arr));
//        System.out.println(Code04_SubArrayMaxSumFollowUp.rob2(arr));

        int times = 100000;
        int len = 300;
        int value = 20;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(len, value);;
            int p1 = method1(arr);
            int p2 = Code04_SubArrayMaxSumFollowUp.rob2(arr);
            if (p1 != p2) {
                System.out.println("oops");
                printArr(arr);
                System.out.println(p1);
                System.out.println(p2);
            }
        }
    }

}

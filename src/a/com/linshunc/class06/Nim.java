package a.com.linshunc.class06;

import class06.Code05_Nim;

import static class06.Code04_MostXorZero.*;

public class Nim {

    // 是否先手赢
    public static boolean method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return false;
        }

        return process(arr);
    }

    private static boolean process(int[] arr) {
        boolean res = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                continue;
            }
            for (int j = 1; j <= arr[i]; j++) {
                int pre = arr[i];
                arr[i] = arr[i] - j;
                if (!process(arr)) {
                    res = true;
                }
                arr[i] = pre;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {2};
//        boolean res = Code05_Nim.printWinner(arr);
//        boolean res2 = method1(arr);
//        System.out.println(res);
//        System.out.println(res2);


        int testTime = 1000;
        int maxSize = 4;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            System.out.println();
            printArray(arr);
            boolean comp = Code05_Nim.printWinner(arr);
            boolean res = method1(arr);
            System.out.println(comp);
            System.out.println(res);
            System.out.println();
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}

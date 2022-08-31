package a.com.linshunc.class04;

import class04.Code05_CandyProblem;

import static a.com.linshunc.class01.AOE.printArr;
import static class04.Code01_QueryHobby.generateRandomArray;

public class CandyProblem {

    public static int method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        int res = 0;

        int[] p1 = new int[N];
        int[] p2 = new int[N];
        p1[0] = 1;
        p2[N - 1] = 1;
        for (int i = 1; i < p1.length; i++) {
            p1[i] = arr[i] > arr[i - 1] ? p1[i - 1] + 1 : 1;
        }
        for (int i = N - 2; i >= 0; i--) {
            p2[i] = arr[i] > arr[i + 1] ? p2[i + 1] + 1 : 1;
        }
        for (int i = 0; i < N; i++) {
            res += Math.max(p1[i], p2[i]);
        }

        return res;
    }

    public static int method1Pro(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        int res = 0;

        int[] p1 = new int[N];
        int[] p2 = new int[N];
        p1[0] = 1;
        p2[N - 1] = 1;
        for (int i = 1; i < p1.length; i++) {
            p1[i] = arr[i] > arr[i - 1] ? p1[i - 1] + 1 : arr[i] == arr[i - 1] ? p1[i - 1] : 1;
        }
        for (int i = N - 2; i >= 0; i--) {
            p2[i] = arr[i] > arr[i + 1] ? p2[i + 1] + 1 : arr[i] == arr[i + 1] ? p2[i + 1] : 1;
        }
        for (int i = 0; i < N; i++) {
            res += Math.max(p1[i], p2[i]);
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {20,8,5,15,6,4,20,8,1,11};
//        System.out.println(method1(arr));
//        System.out.println(Code05_CandyProblem.candy2(arr));

        int times = 100000;
        int len = 300;
        int value = 20;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(len, value);;
            int p1 = method1Pro(arr);
            int p2 = Code05_CandyProblem.candy3(arr);
            if (p1 != p2) {
                System.out.println("oops");
                printArr(arr);
                System.out.println(p1);
                System.out.println(p2);
            }
        }
    }

}

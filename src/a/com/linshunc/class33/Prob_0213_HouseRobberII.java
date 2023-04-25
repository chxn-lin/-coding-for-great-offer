package a.com.linshunc.class33;


import class33.Problem_0213_HouseRobberII;

import java.util.Arrays;

public class Prob_0213_HouseRobberII {

    public static int pickMaxSum2(int[] arr) {
        if (arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }
        int first = arr[0];
        int sec = Math.max(arr[1], arr[0]);
        int res = sec;

        for (int i = 2; i < arr.length - 1; i++) {
            int p1 = sec;
            int p2 = first + arr[i];
            first = sec;
            sec = Math.max(p1, p2);
            res = Math.max(res, sec);
        }
        first = arr[1];
        sec = Math.max(arr[1], arr[2]);
        res = Math.max(res, sec);
        for (int i = 3; i < arr.length; i++) {
            int p1 = sec;
            int p2 = first + arr[i];
            first = sec;
            sec = Math.max(p1, p2);
            res = Math.max(res, sec);
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {5, 13, 17};
//        int i1 = pickMaxSum2(arr);
//        int i2 = Problem_0213_HouseRobberII.rob(arr);
//        System.out.println(i1);
//        System.out.println(i2);

        for (int i = 0; i < 100_0000; i++) {
            int[] arr = generateArr();
            int i1 = pickMaxSum2(arr);
            int i2 = Problem_0213_HouseRobberII.rob(arr);
            if (i1 != i2) {
                System.out.println("Oops");
                System.out.println(Arrays.toString(arr));
                System.out.println(i1);
                System.out.println(i2);
                break;
            }
        }
        System.out.println("Success");
    }

    private static int[] generateArr() {
        int[] arr = new int[(int) (10 * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (20 * Math.random());
        }
        return arr;
    }

    public static int pickMaxSum(int[] arr) {
        if (arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int res = Math.max(process(arr, 0, arr.length - 2), process(arr, 1, arr.length - 1));
        return res;
    }

    private static int process(int[] arr, int index, int R) {
        if (R + 1 <= index) {
            // 到了最末尾
            return 0;
        }
        int p1 = process(arr, index + 1, R);
        int p2 = process(arr, index + 2, R) + arr[index];

        return Math.max(p1, p2);
    }

}

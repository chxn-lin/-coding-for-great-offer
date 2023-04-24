package a.com.linshunc.class32;

import class32.SequenceM;

import java.util.Arrays;

public class MySequenceM {

    public static int ways1(int[] arr, int m) {
        if (arr.length < 1) {
            return 0;
        }

        Arrays.sort(arr);
        int res = 1;
        for (int i = 1; i < arr.length; i++) {
            res = res * (num(arr, i - 1, arr[i] - m) + 1);
        }
        return res;
    }

    private static int num(int[] arr, int endIndex, int num) {
        int L = 0;
        int R = endIndex;
        int lessThenNum = -1;
        while (L <= R) {
            int M = L + ((R - L) >> 1);
            if (arr[M] < num) {
                L = M + 1;
                lessThenNum = M;
            }
            else {
                R = M - 1;
            }
        }
        return endIndex - lessThenNum;
    }


    // 为了测试
    public static void main(String[] args) {
//        int[] arr = {1,4,9};
//        int m = 3;
//        System.out.println(SequenceM.ways3(arr, m));
//        System.out.println(ways1(arr, m));

        int N = 10;
        int value = 20;
        int testTimes = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * (N + 1));
            int[] arr = SequenceM.randomArray(len, value);
            int[] arr2 = copyArr(arr);
            int m = (int) (Math.random() * (value + 1));
            int ans1 = ways1(arr, m);
            int ans3 = SequenceM.ways3(arr2, m);
            if (ans1 != ans3) {
                System.out.println("出错了!");
                System.out.println(Arrays.toString(arr2));
                System.out.println(m);
                System.out.println(ans1);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int[] copyArr(int[] arr) {
        int[] ints = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ints[i] = arr[i];
        }
        return ints;
    }

}

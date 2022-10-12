package a.com.linshunc.class07;

import static class07.Code01_MaxAndValue.maxAndValue2;
import static class07.Code01_MaxAndValue.randomArray;

public class MaxAndValue {

    public static int method1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = 0;

        int temp = 0;
        int N = arr.length - 1;
        for (int i = 30; i >= 0; i--) {
            temp = N;
            for (int j = 0; j <= N;) {
                if (((arr[j] >> i) & 1) == 1) {
                    j++;
                } else {
                    sw(arr, N, j);
                    N--;
                }
            }
            if (N + 1 > 2) {
                res |= (1 << i);
            } else if (N + 1 == 2) {
                return arr[0] & arr[1];
            } else {
                N = temp;
            }
        }

        return res;
    }

    private static void sw(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[] arr = randomArray(size, range);
            int ans1 = method1(arr);
            int ans2 = maxAndValue2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }

}

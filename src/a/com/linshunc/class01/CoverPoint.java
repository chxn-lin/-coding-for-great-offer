package a.com.linshunc.class01;

import static class01.Code01_CordCoverMaxPoint.generateArray;
import static class01.Code01_CordCoverMaxPoint.test;

public class CoverPoint {

    public static int method1(int[] arr, int k){
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int res = 1;
        int L = 0;
        int R = 0;
        while (R != arr.length && L <= R) {
            if (arr[R] - arr[L] <= k) {
                res = Math.max(res, R - L + 1);
                R++;
            } else {
                L++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = {1,3,4,5,8,10};
//        int k = 3;
//        System.out.println(method1(arr, k));
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans2 = method1(arr, L);
            int ans3 = test(arr, L);
            if (ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }

    }

}

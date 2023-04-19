package a.com.linshunc.class29;

import class29.Problem_0069_SqrtX;

import java.util.Random;

public class Prob_0069_SqrtX {

    public static int mySqrt(int x) {
        if (x < 1) {
            return 0;
        }
        if (x < 2) {
            return 1;
        }
        int L = 0;
        int R = x;
        int M = 0;
        int res = 1;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (M * M > x) {
                R = M - 1;
            }
            else {
                res = M;
                L = M + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int times = 100_0000;
        for (int i = 0; i < times; i++) {
            int x = (int) (Math.random() * 200);
            if (mySqrt(x) != Problem_0069_SqrtX.mySqrt(x)) {
                System.out.println("Oops");
                System.out.println(x);
                return;
            }
        }
        System.out.println("Success");
    }

}

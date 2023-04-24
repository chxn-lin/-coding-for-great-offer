package a.com.linshunc.class32;

import class32.Problem_0191_NumberOf1Bits;

public class Prob_0191_NumberOf1Bits {

    public static int hammingWeight1(int n) {
        int res = 0;
        int mostRightOne = n & (-n);
        while (mostRightOne != 0) {
            res++;
            n = n ^ mostRightOne;
            mostRightOne = n & (-n);
        }
        return res;
    }

    public static void main(String[] args) {
//        int n = 100;
//        System.out.println(Problem_0191_NumberOf1Bits.hammingWeight1(n));
//        System.out.println(hammingWeight1(n));

        for (int i = -100_0000; i < 100_0000; i++) {
            int i1 = hammingWeight1(i);
            int i2 = Problem_0191_NumberOf1Bits.hammingWeight1(i);
            if (i1 != i2) {
                System.out.println("Oops");
                System.out.println(i1);
                System.out.println(i2);
                System.out.println(i);
                break;
            }
        }
    }

}

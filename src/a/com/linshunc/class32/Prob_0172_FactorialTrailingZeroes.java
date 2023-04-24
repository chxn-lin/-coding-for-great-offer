package a.com.linshunc.class32;

import class32.Problem_0172_FactorialTrailingZeroes;

public class Prob_0172_FactorialTrailingZeroes {

    public static int trailingZeroes(int n) {
        int res = 0;
        int mul = 5;
        while (mul <= n) {
            res += n/mul;
            mul *= 5;
        }
        return res;
    }

    public static void main(String[] args) {
//        int n = 25;
//        int i = trailingZeroes(n);
//        int i2 = Problem_0172_FactorialTrailingZeroes.trailingZeroes(n);
//        System.out.println(i);
//        System.out.println(i2);

        for (int n = 1; n < 100_0000; n++) {
            int i = trailingZeroes(n);
            int i2 = Problem_0172_FactorialTrailingZeroes.trailingZeroes(n);
            if (i != i2) {
                System.out.println("Oops");
                System.out.println(n);
                break;
            }
        }

    }

}

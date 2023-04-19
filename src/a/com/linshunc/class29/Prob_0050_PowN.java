package a.com.linshunc.class29;

public class Prob_0050_PowN {

    public static int pow(int a, int n) {
        int base = a;
        int res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= base;
            }
            base *= base;
            n = n >> 1;
        }
        return res;
    }

    public static double myPow(double x, int n) {
        double base = x;
        double res = 1D;
        n = -n;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= base;
            }
            base *= base;
            n = n >> 1;
        }
        return 1D / res;
    }

    public static void main(String[] args) {
        int a = 5;
        int n = -9;
        System.out.println(Math.pow(a, n));
        System.out.println(myPow(a, n));
    }

}

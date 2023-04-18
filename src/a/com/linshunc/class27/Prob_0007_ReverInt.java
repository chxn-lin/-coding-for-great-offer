package a.com.linshunc.class27;

import class27.Problem_0007_ReverseInteger;

public class Prob_0007_ReverInt {

    public static int reverse(int x) {
        boolean isRight = x >= 0 ? true : false;
        x = isRight ? -x : x;
        int minDiv = Integer.MIN_VALUE / 10;
        int minSub = Integer.MIN_VALUE % 10;
        int res = 0;
        while (x != 0) {
            if (res < minDiv || (res == minDiv && x % 10 < minSub)) {
                return 0;
            }
            res = res * 10 + x % 10;
            x = x/10;
        }
        return isRight ? -res : res;
    }

    public static void main(String[] args) {
        int x = -123;
        System.out.println(reverse(x));
        System.out.println(Problem_0007_ReverseInteger.reverse(x));
    }

}

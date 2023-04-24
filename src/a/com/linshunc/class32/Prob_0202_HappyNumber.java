package a.com.linshunc.class32;

import class32.Problem_0202_HappyNumber;

import java.util.HashSet;
import java.util.Set;

public class Prob_0202_HappyNumber {

    public static boolean isHappy1(int n) {
        Set set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            n = getNextN(n);
        }
        return n == 1 ? true : false;
    }

    private static int getNextN(int n) {
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n = n / 10;
        }
        return res;
    }

    public static boolean isHappy2(int n) {
        while (n != 4 && n != 1) {
            n = getNextN(n);
        }
        return n == 1 ? true : false;
    }

    public static void main(String[] args) {
//        boolean i1 = isHappy1(7);
//        boolean i2 = Problem_0202_HappyNumber.isHappy1(7);
//        System.out.println(i1);
//        System.out.println(i2);
//    }
        for (int i = 1; i < 100_0000; i++) {
            boolean i1 = isHappy2(i);
            boolean i2 = Problem_0202_HappyNumber.isHappy1(i);
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

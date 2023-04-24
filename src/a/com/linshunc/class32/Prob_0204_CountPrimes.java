package a.com.linshunc.class32;

import class32.Problem_0204_CountPrimes;

public class Prob_0204_CountPrimes {

    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] arr = new boolean[n];
        int count = n/2;
        for (int i = 3; i < n; i += 2) {
            for (int j = i * i; j < n; j += 2 * i) {
                if (!arr[j]) {
                    count--;
                    arr[j] = true;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1_0000; i++) {
            int i1 = countPrimes(i);
            int i2 = Problem_0204_CountPrimes.countPrimes(i);
            if (i1 != i2) {
                System.out.println("Oops");
                System.out.println(i1);
                System.out.println(i2);
                System.out.println(i);
                break;
            }
        }
        System.out.println("Success");
    }

}

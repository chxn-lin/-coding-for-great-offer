package a.com.linshunc.class01;

import class01.Code04_MinSwapStep;

public class MinSwapNOrG {

    public static int method1(String str){
        char[] arr = str.toCharArray();
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int L1 = 0;
        int L2 = 0;
        int step1 = 0;
        int step2 = 0;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == 'G') {
                step1 += i - L1;
                L1++;
            } else {
                step2 += i - L2;
                L2++;
            }
        }

        return Math.min(step1, step2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String gen = gen(10);
            int i1 = method1(gen);
            int i2 = Code04_MinSwapStep.minSteps2(gen);
            if (i1 != i2) {
                System.out.println("oops");
                System.out.println(gen);
                System.out.println(i1);
                System.out.println(i2);
                break;
            }
        }
    }

    public static String gen(int maxLen) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            str.append(Math.random() < 0.5 ? "G" : "B");
        }
        return str.toString();
    }

}

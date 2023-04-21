package a.com.linshunc.class30;

import class30.Problem_0091_DecodeWays;

public class Prob_0091_DecodeWays {

    public static int numDecodings1(String s) {
        if (s.length() < 1) {
            return 0;
        }
        char[] arr = s.toCharArray();
        return process(arr, 0);
    }

    private static int process(char[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        if (arr[index] == '0') {
            return 0;
        }
        int p1 = process(arr, index + 1);
        int p2 = 0;
        if (index < arr.length - 1) {
            int curNum = (arr[index] - '0') * 10 + arr[index + 1] - '0';
            if (curNum < 27) {
                p2 = process(arr, index + 2);
            }
        }
        return p1 + p2;
    }

    public static int numDecodings2(String s) {
        if (s.length() < 1) {
            return 0;
        }
        char[] arr = s.toCharArray();

        int last = 1;// 上个
        int lastLast = 1;// 上上个
        if (arr[arr.length - 1] == '0') {
            last = 0;
        }
        else {
            last = 1;
        }

        for (int index = arr.length - 2; index >= 0; index--) {
            if (arr[index] == '0') {
                lastLast = last;
                last = 0;
            }
            else {
                int p1 = last;
                int p2 = 0;
                if (index < arr.length - 1) {
                    int curNum = (arr[index] - '0') * 10 + arr[index + 1] - '0';
                    if (curNum < 27) {
                        p2 = lastLast;
                    }
                }
                lastLast = last;
                last = p1 + p2;
            }
        }
        return last;
    }

    public static void main(String[] args) {
//        String str = "8110";
//        System.out.println(numDecodings2(str));
//        System.out.println(Problem_0091_DecodeWays.numDecodings1(str));

        int times = 100_0000;
        for (int i = 0; i < times; i++) {
            String str = randomStr(10);
            int i1 = numDecodings2(str);
            int i2 = Problem_0091_DecodeWays.numDecodings1(str);
            if (i1 != i2) {
                System.out.println("Oops");
                System.out.println(i1);
                System.out.println(i2);
                System.out.println(str);
                return;
            }
        }
        System.out.println("Success");
    }

    public static String randomStr(int maxLen){
        int len = (int) ((Math.random() * maxLen) + 1);
        int[] arr = new int[len];

        int first = (int) ((Math.random() * 9) + 1);
        StringBuilder str = new StringBuilder(first);
        for (int i = 1; i < len; i++) {
            str.append((int) (Math.random() * 10));
        }
        return str.toString();
    }

}

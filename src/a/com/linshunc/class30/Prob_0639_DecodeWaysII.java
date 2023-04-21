package a.com.linshunc.class30;

import class30.Problem_0639_DecodeWaysII;

public class Prob_0639_DecodeWaysII {

    static int div = 10_0000_0000 + 7;

    public static int numDecodings0(String str){
        if (str.length() < 1) {
            return 0;
        }
        char[] arr = str.toCharArray();
        return process(arr, 0);
    }

    private static int process(char[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        if (arr[index] == '0') {
            return 0;
        }
        long res = process(arr, index + 1) * (arr[index] == '*' ? 9 : 1);
        if (index < arr.length - 1) {
            // 有下一个
            if (arr[index] == '*') {
                if (arr[index + 1] == '*') {
                    // 两个都等于*
                    res += 15 * process(arr, index + 2);
                }
                else {
                    // 后面的不为*
                    res += (arr[index + 1] - '0' < 7 ? 2 : 1) * process(arr, index + 2);
                }
            }
            else {
                if (arr[index + 1] == '*') {
                    int cur = arr[index] - '0';
                    res += (cur == 1 ? 9 : cur == 2 ? 6 : 0) * process(arr, index + 2);
                }
                else {
                    int curNum = (arr[index] - '0') * 10 + arr[index + 1] - '0';
                    if (curNum < 27) {
                        res += process(arr, index + 2);
                    }
                }
            }
        }
        return (int) (res % div);
    }

    public static void main(String[] args) {

//        String str = "**";
//        System.out.println(numDecodings0(str));
//        System.out.println(Problem_0639_DecodeWaysII.numDecodings1(str));

        int times = 100_0000;
        for (int i = 0; i < times; i++) {
            String str = randomStr(10);
            int i1 = numDecodings0(str);
            int i2 = Problem_0639_DecodeWaysII.numDecodings1(str);
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

        int first = (int) ((Math.random() * 10) + 1);
        StringBuilder str = new StringBuilder(first == 10 ? "*" : String.valueOf(first));
        for (int i = 1; i < len; i++) {
            int i1 = (int) (Math.random() * 11);
            str.append(i1 == 10 ? "*" : String.valueOf(i1));
        }
        return str.toString();
    }

}

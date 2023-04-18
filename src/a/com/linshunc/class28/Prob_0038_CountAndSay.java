package a.com.linshunc.class28;

import class28.Problem_0038_CountAndSay;

public class Prob_0038_CountAndSay {

    public static String countAndSay(int n) {
        if (n < 1) {
            return "1";
        }
        return process(n);
    }

    private static String process(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder str = new StringBuilder();
        char[] arr = process(n - 1).toCharArray();
        char lastChar = arr[0];
        int lastCount = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == lastChar) {
                lastCount++;
            }
            else {
                // 结算上一个
                str.append((char)('0' + lastCount))
                    .append(lastChar);
                lastCount = 1;
                lastChar = arr[i];
            }
        }
        str.append((char)('0' + lastCount))
                .append(lastChar);

        return str.toString();
    }

    public static void main(String[] args) {
        int k = 10;
        System.out.println(countAndSay(k));
        System.out.println(Problem_0038_CountAndSay.countAndSay(k));
    }

}

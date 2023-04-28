package a.com.linshunc.class35;

public class Code01_StringKth {

    public static int kth(String s, int len) {
        if (s.length() < 1 || len < 1) {
            return -1;
        }
        char[] arr = s.toCharArray();
        int res = 0;
        for (int i = 0, rest = len - 1; i < arr.length; i++, rest--) {
            res += (arr[i] - 'a') * f(rest) + 1;
        }
        return res;
    }

    private static int f(int rest) {
        int res = 1;
        for (int i = 1, base = 26; i <= rest; i++, base *= 26) {
            res += base;
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "ab";
        int len = 3;
        System.out.println(kth(str, len));
        System.out.println(class35.Code01_StringKth.kth(str, len));
    }

}

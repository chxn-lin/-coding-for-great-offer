package a.com.linshunc.class31;

public class Prob_0125_ValidPalindrome {

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(str));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome("r"));
    }

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }
        char[] arr = s.toCharArray();
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            if (!checkIsRight(arr[L])) {
                L++;
                continue;
            }
            if (!checkIsRight(arr[R])) {
                R--;
                continue;
            }
            if (isSame(arr[L], arr[R])) {
                L++;
                R--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    private static boolean checkIsRight(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static boolean isSame(char c, char c1) {
        return c == c1 || (c - 'a' == c1 - 'A') || (c - 'A' == c1 - 'a');
    }




}

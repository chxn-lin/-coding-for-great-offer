package a.com.linshunc.class28;

import class28.Problem_0020_ValidParentheses;

public class Prob_0020_ValidStr {

    public static boolean isValid(String s) {
        char[] arr = s.toCharArray();
        char[] stack = new char[arr.length];
        int index = 0;
        for (char c : arr) {
            switch (c){
                case '(':
                    stack[index++] = ')';
                    break;
                case '{':
                    stack[index++] = '}';
                    break;
                case '[':
                    stack[index++] = ']';
                    break;
                default:
                    if (index == 0 || stack[--index] != c) {
                        return false;
                    }
                    break;
            }
        }
        if (index != 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int times = 1000_0000;
        for (int i = 0; i < times; i++) {
            String s = randomStr();
            boolean valid1 = isValid(s);
            boolean valid2 = Problem_0020_ValidParentheses.isValid(s);
            if (valid1 != valid2) {
                System.out.println("Oops");
                System.out.println(s);
                System.out.println(valid1);
                System.out.println(valid2);
                break;
            }
        }
        System.out.println("Success");
    }

    public static final char[] useChar = {'(', ')', '[', ']', '{', '}'};

    public static String randomStr(){
        int maxLen = 5;
        char[] arr = new char[(int) (Math.random() * maxLen) * 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = useChar[(int) (Math.random() * 6)];
        }
        return String.valueOf(arr);
    }

}

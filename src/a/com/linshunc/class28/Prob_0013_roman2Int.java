package a.com.linshunc.class28;

import class28.Problem_0013_RomanToInteger;

public class Prob_0013_roman2Int {

    public static int romanToInt(String s) {
        char[] arr = s.toCharArray();
        int[] valueArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case 'I':
                    valueArr[i] = 1;
                    break;
                case 'V':
                    valueArr[i] = 5;
                    break;
                case 'X':
                    valueArr[i] = 10;
                    break;
                case 'L':
                    valueArr[i] = 50;
                    break;
                case 'C':
                    valueArr[i] = 100;
                    break;
                case 'D':
                    valueArr[i] = 500;
                    break;
                case 'M':
                    valueArr[i] = 1000;
                    break;
                default:
                    System.out.println("数据错误！");
            }
        }
        int res = 0;
        for (int i = 0; i < valueArr.length - 1; i++) {
            int cur = valueArr[i];
            int nextCur = valueArr[i + 1];
            if (cur < nextCur) {
                cur = -cur;
            }
            res += cur;
        }
        res += valueArr[valueArr.length - 1];

        return res;
    }

    public static void main(String[] args) {
        int times = 100_0000;
        for (int i = 0; i < times; i++) {
            String str = Prob_0012_int2Roman.intToRoman(Prob_0012_int2Roman.random());
            int num1 = romanToInt(str);
            int num2 = Problem_0013_RomanToInteger.romanToInt(str);
            if (num1 != num2) {
                System.out.println("Oops");
                System.out.println(str);
                System.out.println(num1);
                System.out.println(num2);
                return;
            }
        }
        System.out.println("success");
    }

}

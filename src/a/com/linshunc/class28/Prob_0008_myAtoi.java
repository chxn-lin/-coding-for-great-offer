package a.com.linshunc.class28;

import class28.Problem_0008_StringToInteger;

public class Prob_0008_myAtoi {

    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）
     * 函数 myAtoi(string s) 的算法如下：
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为231 − 1。
     * 返回整数作为最终结果。
     * 注意：本题中的空白字符只包括空格字符 ' ' 。除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     */

    public static int myAtoi(String s) {
        // 处理逻辑需要补充
        s = removeHeadSpace(s);

        char[] arr = s.toCharArray();
        // 处理正常的数
        // 是否是正数
        int minA = Integer.MIN_VALUE / 10;
        int minB = Integer.MIN_VALUE % 10;

        boolean posi = arr[0] == '-' ? false : true;
        int res = 0;
        int index = posi ? 0 : 1;
        while (index != arr.length) {
            char cur = arr[index++];
            if (res < minA || (res == minA && '0' - cur < minB)) {
                return posi ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + ('0' - cur);
        }

        return posi ? -res : res;
    }

    public static String removeHeadSpace(String s) {
        return s.trim();
    }

    public static void main(String[] args) {
        String str1 = "-2147483647";
        System.out.println(myAtoi(str1));
        System.out.println(Problem_0008_StringToInteger.myAtoi(str1));
        String str2 = "-214748367";
        System.out.println(myAtoi(str2));
        System.out.println(Problem_0008_StringToInteger.myAtoi(str2));
        String str3 = "2147483646";
        System.out.println(myAtoi(str3));
        System.out.println(Problem_0008_StringToInteger.myAtoi(str3));
        String str4 = "-2147483648";
        System.out.println(myAtoi(str4));
        System.out.println(Problem_0008_StringToInteger.myAtoi(str4));
    }

}

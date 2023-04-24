package a.com.linshunc.class32;

import java.util.HashMap;
import java.util.Map;

public class Prob_0166_FractionToRecurringDecimal {

    public static void main(String[] args) {
        int num = 4;
        int den = 333;
        System.out.println(fractionToDecimal(num, den));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return "0";
        }
        StringBuilder str = new StringBuilder();
        int curNum = numerator/denominator;
        numerator %= denominator;
        str.append(curNum);
        if (numerator > 0) {
            str.append(".");
        }
        StringBuilder endStr = new StringBuilder();
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        while (numerator > 0) {
            numerator = numerator * 10;
            curNum = numerator/denominator;
            numerator %= denominator;
            if (map.containsKey(numerator)) {
                // 存在，那么插入
                int startIndex = map.get(numerator);
                endStr.insert(startIndex, "{");
                endStr.append("}");
                break;
            }
            else {
                endStr.append(curNum);
                map.put(numerator, index);
            }
            index++;
        }

        return str.append(endStr.toString()).toString();
    }

}

package a.com.linshunc.class10;

import class10.Code05_BooleanEvaluation;
import javafx.util.Pair;

public class Code05_GetBooleanRes {

    public static int countEval0(String express, int desired) {
        if (express == null || express.length() < 1) {
            return 0;
        }
        char[] chars = express.toCharArray();
        Pair<Integer, Integer> pair = process(chars, 0, chars.length - 1);
        return desired == 1 ? pair.getKey() : pair.getValue();
    }

    private static Pair<Integer, Integer> process(char[] chars, int start, int end) {
        if (start == end) {
            int trueFlag = chars[start] == '1' ? 1 : 0;
            return new Pair<>(trueFlag, trueFlag ^ 1);
        }
        else {
            int key = 0;
            int value = 0;

            for (int mid = start + 1; mid < end; mid+=2) {

                Pair<Integer, Integer> left = process(chars, start, mid - 1);
                Pair<Integer, Integer> right = process(chars, mid + 1, end);
                Integer leftTrue = left.getKey();
                Integer leftFalse = left.getValue();

                Integer rightTrue = right.getKey();
                Integer rightFalse = right.getValue();

                if (chars[mid] == '&') {
                    key += leftTrue * rightTrue;
                    value += leftFalse * (rightFalse + rightTrue) + leftTrue * rightFalse;
                }
                else if (chars[mid] == '|') {
                    key += leftTrue * (rightFalse + rightTrue) + leftFalse * rightTrue;
                    value += leftFalse * rightFalse;
                }
                else if (chars[mid] == '^') {
                    key += leftTrue * rightFalse + leftFalse * rightTrue;
                    value += leftFalse * rightFalse + leftTrue * rightTrue;
                }
            }


            return new Pair<>(key, value);
        }
    }

    public static void main(String[] args) {
        String str = "1|0^1&0&0|0|1";
        String str2 = "1|0^1&0&0|0|1^0";

        System.out.println(countEval0(str, 1));
        System.out.println(countEval0(str, 0));
        System.out.println(countEval0(str2, 1));
        System.out.println(countEval0(str2, 0));

        System.out.println();

        System.out.println(Code05_BooleanEvaluation.countEval2(str, 1));
        System.out.println(Code05_BooleanEvaluation.countEval2(str, 0));
        System.out.println(Code05_BooleanEvaluation.countEval2(str2, 1));
        System.out.println(Code05_BooleanEvaluation.countEval2(str2, 0));
    }

}

package a.com.linshunc.class28;

import class28.Problem_0017_LetterCombinationsOfAPhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class Prob_0017_PhoneNumber {

    public static final char[][] phoneArr = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };

    public static List<String> letterCombinations(String digits) {
        char[] arr = digits.toCharArray();
        List<String> list = new ArrayList<>();
        char[] path = new char[arr.length];

        process(arr, 0, path, list);
        return list;
    }

    public static void process(char[] arr, int index, char[] path, List<String> list) {
        if (index == arr.length) {
            list.add(String.valueOf(path));
            return;
        }
        for (char c : phoneArr[arr[index] - '2']) {
            path[index] = c;
            process(arr, index + 1, path, list);
        }
    }

    public static void main(String[] args) {
        int times = 100_0000;
        for (int i = 0; i < times; i++) {
            String s = randomNumStr(4);
            List<String> list1 = letterCombinations(s);
            List<String> list2 = Problem_0017_LetterCombinationsOfAPhoneNumber.letterCombinations(s);
            if (list1.size() == list2.size() && list1.containsAll(list2)) {

            }
            else {
                System.out.println("Oops");
                System.out.println(s);
                System.out.println(list1);
                System.out.println(list2);
                return;
            }
        }
        System.out.println("Success");
    }

    public static String randomNumStr(int maxLen){
        int curLen = (int) (Math.random() * maxLen) + 1;
        char[] arr = new char[curLen];
        for (int i = 0; i < curLen; i++) {
            arr[i] = (char) ('2' + ((int)(Math.random() * 8)));
        }

        return String.valueOf(arr);
    }

}

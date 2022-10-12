package a.com.linshunc.class07;

import class07.Code05_WorldBreak;

import java.util.HashSet;
import java.util.Set;

import static class07.Code05_WorldBreak.generateRandomSample;

public class WorldBreak {

    /*
     *
     * 假设所有字符都是小写字母. 大字符串是str. arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
     * 使用arr中的单词有多少种拼接str的方式. 返回方法数.
     *
     */

    public static int ways1(String str, String[] arr) {
        Set<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s);
        }

        return process(str, set, 0);
    }

    private static int process(String str, Set<String> set, int index) {
        if (index == str.length()) {
            return 1;
        }
        int res = 0;
        for (int end = index; end < str.length(); end++) {
            String pre = str.substring(index, end + 1);
            if (set.contains(pre)) {
                res += process(str, set, end + 1);
            }
        }
        return res;
    }

    public static int ways2(String str, String[] arr) {
        Set<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s);
        }
        int[] intArr = new int[str.length() + 1];
        for (int i = 0; i < intArr.length; i++) {
         intArr[i] = -1;
        }
        return process2(str, set, 0, intArr);
    }

    private static int process2(String str, Set<String> set, int index, int[] intArr) {
        if (intArr[index] != -1) {
            return intArr[index];
        }
        int res = 0;
        if (index == str.length()) {
            res = 1;
        } else {
            for (int end = index; end < str.length(); end++) {
                String pre = str.substring(index, end + 1);
                if (set.contains(pre)) {
                    res += process2(str, set, end + 1, intArr);
                }
            }
        }
        intArr[index] = res;
        return res;
    }

    private static class Node {
        boolean isEnd;
        Node[] nextArr;

        public Node() {
            this.isEnd = false;
            this.nextArr = new Node[26];
        }
    }

    public static int ways3(String str, String[] arr) {
        Node head = new Node();
        for (String s : arr) {
            char[] chars = s.toCharArray();
            Node cur = head;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                int index = c - 'a';
                if (cur.nextArr[index] == null) {
                    cur.nextArr[index] = new Node();
                }
                cur = cur.nextArr[index];
                if (i == chars.length - 1) {
                    cur.isEnd = true;
                }
            }
        }
        int[] intArr = new int[str.length() + 1];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = -1;
        }
        return process3(str.toCharArray(), head, 0, intArr);
    }

    private static int process3(char[] charArr, Node head, int index, int[] intArr) {
        if (intArr[index] != -1) {
            return intArr[index];
        }
        int res = 0;
        if (index == charArr.length) {
            res = 1;
        } else {
            Node cur = head;
            for (int end = index; end < charArr.length; end++) {
                int path = charArr[end] - 'a';
                cur = cur.nextArr[path];
                if (cur == null) {
                    break;
                }
                if (cur.isEnd) {
                    res += process3(charArr, head, end + 1, intArr);
                }
            }
        }
        intArr[index] = res;
        return res;
    }

    public static void main(String[] args) {
        char[] candidates = { 'a', 'b' };
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            Code05_WorldBreak.RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways2(sample.str, sample.arr);
            int ans4 = ways3(sample.str, sample.arr);
            if (ans1 != ans4) {
                testResult = false;
            }
        }
        System.out.println(testTimes + "次随机测试是否通过：" + testResult);
    }

}

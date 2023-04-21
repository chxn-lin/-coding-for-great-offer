package a.com.linshunc.class31;

import class31.Problem_0139_WordBreak;

import java.util.Arrays;
import java.util.List;

public class Prob_0139_WordBreak {

    public static void main(String[] args) {
//        String s = "catsandog";
//        List<String> list = Arrays.asList("cats", "dog", "sand", "and", "cat");
        String s = "catsanddog";
        List<String> list = Arrays.asList("ca", "cas", "and", "sand", "dog");
        System.out.println(wordBreak2(s, list));
        System.out.println(Problem_0139_WordBreak.wordBreak1(s, list));
    }

    public static boolean wordBreak2(String s, List<String> wordDict) {
        Tree tree = generateTree(wordDict);
        char[] arr = s.toCharArray();

        boolean[] dp = new boolean[arr.length + 1];
        dp[arr.length] = true;

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (checkInTree(tree, arr, i2, i)) {
                    dp[i2] |= dp[i + 1];
                }
            }
        }
        return dp[0];
    }

    private static class Tree{
        Tree[] nexts;
        boolean isEnd;
        String str;
        public Tree() {
            nexts = new Tree[26];
            isEnd = false;
        }
    }

    public static boolean wordBreak1(String s, List<String> wordDict) {
        Tree tree = generateTree(wordDict);
        char[] arr = s.toCharArray();

        return process(tree, arr, 0);
    }

    private static boolean process(Tree tree, char[] arr, int start) {
        if (start == arr.length) {
            return true;
        }
        boolean res = false;
        for (int i = start + 1; i < arr.length; i++) {
            if (checkInTree(tree, arr, start, i)) {
                if (process(tree, arr, i + 1)) {
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    private static boolean checkInTree(Tree tree, char[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (tree.nexts[arr[i] - 'a'] != null) {
                tree = tree.nexts[arr[i] - 'a'];
            }
            else {
                return false;
            }
        }
        return tree.isEnd ? true : false;
    }


    private static Tree generateTree(List<String> wordDict) {
        Tree tree = new Tree();
        for (String s : wordDict) {
            Tree cur = tree;
            char[] chars = s.toCharArray();
            if (s.length() == 0) {
                continue;
            }
            for (int i = 0; i < chars.length - 1; i++) {
                char aChar = chars[i];
                if (cur.nexts[aChar - 'a'] == null) {
                    cur.nexts[aChar - 'a'] = new Tree();
                }
                cur = cur.nexts[aChar - 'a'];
            }
            if (cur.nexts[chars[chars.length - 1] - 'a'] == null) {
                cur.nexts[chars[chars.length - 1] - 'a'] = new Tree();
            }
            cur = cur.nexts[chars[chars.length - 1] - 'a'];
            cur.isEnd = true;
            cur.str = s;
        }
        return tree;
    }

}

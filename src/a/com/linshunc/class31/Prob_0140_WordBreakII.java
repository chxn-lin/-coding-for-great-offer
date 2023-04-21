package a.com.linshunc.class31;

import class31.Problem_0139_WordBreak;
import class31.Problem_0140_WordBreakII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prob_0140_WordBreakII {

    public static void main(String[] args) {
//        String s = "catsandog";
//        List<String> list = Arrays.asList("cats", "dog", "sand", "and", "cat");
        String s = "pineapplepenapple";
        List<String> list = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreak(s, list));
        System.out.println(Problem_0140_WordBreakII.wordBreak(s, list));
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

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Tree tree = generateTree(wordDict);
        char[] arr = s.toCharArray();

        // 是否可以分割
        boolean[] dp = getDp(tree, arr);
        List<String> res = new ArrayList<>();

        process(tree, arr, dp, res, 0, new ArrayList<String>());
        return res;
    }

    private static void process(Tree tree, char[] arr, boolean[] dp, List<String> list, int index, List<String> path) {
        if (index == arr.length) {
            list.add(initPath(path));
        }
        for (int i = index + 1; i < arr.length; i++) {
            String str = checkInTree(tree, arr, index, i);
            if (!str.equals("") && dp[i + 1]) {
                path.add(str);
                process(tree, arr, dp, list, index + str.length(), path);
                path.remove(path.size() - 1);
            }
        }
    }

    public static boolean[] getDp(Tree tree, char[] arr) {

        boolean[] dp = new boolean[arr.length + 1];
        dp[arr.length] = true;

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (!checkInTree(tree, arr, i2, i).equals("")) {
                    dp[i2] |= dp[i + 1];
                }
            }
        }
        return dp;
    }

    private static String initPath(List<String> path) {
        StringBuilder str = new StringBuilder();
        if (path.size() > 0) {
            for (int i = 0; i < path.size() - 1; i++) {
                str.append(path.get(i) + " ");
            }
            str.append(path.get(path.size() - 1));
        }
        return str.toString();
    }

    private static String checkInTree(Tree tree, char[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (tree.nexts[arr[i] - 'a'] != null) {
                tree = tree.nexts[arr[i] - 'a'];
            }
            else {
                return "";
            }
        }
        return tree.isEnd ? tree.str : "";
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

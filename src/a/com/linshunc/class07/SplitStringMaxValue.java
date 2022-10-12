package a.com.linshunc.class07;

import java.util.HashMap;
import java.util.Map;

public class SplitStringMaxValue {

    public static int maxRecord1(String str, int K, String[] parts, int[] record) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            map.put(parts[i], record[i]);
        }
        return process(str, map, 0, K);
    }

    private static int process(String str, Map<String, Integer> map, int index, int rest) {
        if (index == str.length()) {
            return rest == 0 ? 0 : -1;
        }
        int res = -1;
        for (int end = index; end < str.length(); end++) {
            String firstStr = str.substring(index, end + 1);
            if (map.containsKey(firstStr)) {
                int next = process(str, map, end + 1, rest - 1);
                res = next == -1 ? res : Math.max(res, map.get(firstStr) + next);
            }
        }
        return res;
    }

    // 动态规划解
    public static int maxRecord2(String str, int K, String[] parts, int[] record) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            map.put(parts[i], record[i]);
        }
        int[][] dp = new int[str.length() + 1][K + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int k = 0; k < dp[0].length; k++) {
                dp[i][k] = -1;
            }
        }
        dp[str.length()][0] = 0;
        for (int rest = 1; rest < dp[0].length; rest++) {
            for (int index = str.length() - 1; index >= 0; index--) {
                int res = -1;
                for (int end = index; end < str.length(); end++) {
                    String firstStr = str.substring(index, end + 1);
                    if (map.containsKey(firstStr)) {
                        int next = dp[end + 1][rest - 1];
                        res = next == -1 ? res : Math.max(res, map.get(firstStr) + next);
                    }
                }
                dp[index][rest] = res;
            }
        }


        return dp[0][K];
    }

    private static class Node {
        int value;
        Node[] nextArr;

        public Node() {
            this.value = -1;
            this.nextArr = new Node[26];
        }
    }

    // 动态规划 + 前缀树优化
    public static int maxRecord3(String str, int K, String[] parts, int[] record) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] arr = str.toCharArray();
        Node root = new Node();
        for (int i = 0; i < parts.length; i++) {
            Node cur = root;
            char[] chars = parts[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                int path = chars[j] - 'a';
                if (cur.nextArr[path] == null) {
                    cur.nextArr[path] = new Node();
                }
                cur = cur.nextArr[path];
                if (j == chars.length - 1) {
                    cur.value = record[i];
                }
            }
        }

        int[][] dp = new int[str.length() + 1][K + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int k = 0; k < dp[0].length; k++) {
                dp[i][k] = -1;
            }
        }
        dp[str.length()][0] = 0;
        for (int rest = 1; rest < dp[0].length; rest++) {
            for (int index = str.length() - 1; index >= 0; index--) {
                Node cur = root;
                int res = -1;
                for (int end = index; end < str.length(); end++) {
                    int path = arr[end] - 'a';
                    cur = cur.nextArr[path];
                    if (cur == null) {
                        break;
                    } else {
                        int next = dp[end + 1][rest - 1];
                        res = next == -1 ? res : Math.max(res, cur.value + next);
                    }
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][K];
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        int K = 3;
        String[] parts = { "c", "abc", "def", "g", "ab", "cd", "efg", "defg" };
        int[] record = {1, 1, 1, 1, 3, 3, 3, 1 };
        System.out.println(maxRecord2(str, K, parts, record));
        System.out.println(maxRecord3(str, K, parts, record));
    }

}

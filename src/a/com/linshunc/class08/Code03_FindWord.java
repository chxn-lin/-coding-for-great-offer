package a.com.linshunc.class08;

import static class08.Code03_FindWordInMatrix.findWord2;

public class Code03_FindWord {

    public static boolean findWordCanSame(char[][] m, String word) {
        if (word == null || word.length() < 1) {
            return true;
        }
        if (m == null || m.length < 1 || m[0].length < 1) {
            return false;
        }
        boolean res = false;
        char[] arr = word.toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (process(m, arr, i, j, 0)) {
                    res = true;
                    break;
                }
            }
        }

        return res;
    }

    private static boolean process(char[][] m, char[] arr, int i, int j, int index) {
        if (index == arr.length) {
            return true;
        }

        if (i < 0 || i > m.length - 1 || j < 0 || j > m[0].length - 1 || m[i][j] != arr[index]) {
            return false;
        }

        boolean res = false;
        if (process(m, arr, i + 1, j, index + 1)
            || process(m, arr, i - 1, j, index + 1)
            || process(m, arr, i, j - 1, index + 1)
            || process(m, arr, i, j + 1, index + 1)
        ) {
            res = true;
        }

        return res;
    }

    public static boolean findWordCanntSame(char[][] m, String word) {
        if (word == null || word.length() < 1) {
            return true;
        }
        if (m == null || m.length < 1 || m[0].length < 1) {
            return false;
        }
        boolean res = false;
        char[] arr = word.toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (process2(m, arr, i, j, 0)) {
                    res = true;
                    break;
                }
            }
        }

        return res;
    }

    private static boolean process2(char[][] m, char[] arr, int i, int j, int index) {
        if (index == arr.length) {
            return true;
        }
        if (i < 0 || i > m.length - 1 || j < 0 || j > m[0].length - 1 || m[i][j] == 0 || m[i][j] != arr[index]) {
            return false;
        }
        char pre = m[i][j];
        m[i][j] = 0;
        boolean res = false;
        if (process2(m, arr, i + 1, j, index + 1)
                || process2(m, arr, i - 1, j, index + 1)
                || process2(m, arr, i, j - 1, index + 1)
                || process2(m, arr, i, j + 1, index + 1)
        ) {
            res = true;
        }

        m[i][j] = pre;

        return res;
    }

    public static void main(String[] args) {
        char[][] m = {
                { 'a', 'b', 'z' },
                { 'c', 'd', 'o' },
                { 'f', 'e', 'o' },
        };
        String word1 = "zoooz";
        String word2 = "zoo";
        // 可以走重复路的设定
//        System.out.println(findWord1(m, word1));
//        System.out.println(findWord1(m, word2));
        // 不可以走重复路的设定
        System.out.println(findWord2(m, word1));
        System.out.println(findWord2(m, word2));
        System.out.println(findWordCanntSame(m, word1));
        System.out.println(findWordCanntSame(m, word2));

    }

}

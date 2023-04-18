package a.com.linshunc.class28;

import class28.Problem_0014_LongestCommonPrefix;

public class Prob_0014_LongestComPre {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String first = strs[0];
        char[] firstArr = first.toCharArray();
        int curCommonPre = first.length();
        for (int i = 1; i < strs.length; i++) {
            String cur = strs[i];
            char[] curArr = cur.toCharArray();
            curCommonPre = Math.min(curArr.length, curCommonPre);
            for (int i1 = 0; i1 < curCommonPre; i1++) {
                if (curArr[i1] != firstArr[i1]) {
                    curCommonPre = i1;
                    break;
                }
            }
        }

        return strs[0].substring(0, curCommonPre);
    }

    public static void main(String[] args) {
        String[] arr = {"abbca", "abbbbbcca"};

        System.out.println(longestCommonPrefix(arr));
        System.out.println(Problem_0014_LongestCommonPrefix.longestCommonPrefix(arr));
    }

}

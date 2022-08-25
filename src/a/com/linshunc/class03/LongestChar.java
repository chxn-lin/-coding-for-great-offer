package a.com.linshunc.class03;

import class03.Code01_LongestSubstringWithoutRepeatingCharacters;

public class LongestChar {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int res = 1;
        int[] lastIndex = new int[256];
        for (int i = 0; i < lastIndex.length; i++) {
            lastIndex[i] = -1;
        }
        int pre = 0;
        for (int i = 0; i < str.length; i++) {
            pre = Math.min(i - lastIndex[str[i]], pre + 1);
            res = Math.max(res, pre);
            lastIndex[str[i]] = i;
        }

        return res;
    }

    public static void main(String[] args) {
        String str = "jnakjfghiasdh125.,;;..;//";
        System.out.println(lengthOfLongestSubstring(str));
        System.out.println(Code01_LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(str));
    }

}

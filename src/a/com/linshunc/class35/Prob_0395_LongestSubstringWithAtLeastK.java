package a.com.linshunc.class35;

import class35.Problem_0395_LongestSubstringWithAtLeastKRepeatingCharacters;

import java.util.HashMap;
import java.util.Map;

public class Prob_0395_LongestSubstringWithAtLeastK {

    public static int longestSubstring1(String s, int k) {
        if (k < 1 || s.length() < 1) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int ans = 0;
        for (int i = 1; i <= 26; i++) {
            int[] countArr = new int[26];
            int typeCount = 0;
            int usefulCount = 0;
            int R = -1;
            for (int L = 0; L < arr.length; L++) {
                while (R + 1 < arr.length && !(typeCount == k && countArr[arr[R + 1] - 'a'] == 0)) {
                    R++;
                    if (countArr[arr[R] - 'a'] == 0) {
                        typeCount++;
                    }
                    if (countArr[arr[R] - 'a'] == k - 1) {
                        usefulCount++;
                    }
                    countArr[arr[R] - 'a']++;
                }
                if (usefulCount == i) {
                    ans = Math.max(ans, R - L + 1);
                }
                if (countArr[arr[L] - 'a'] == 1) {
                    typeCount--;
                }
                if (countArr[arr[L] - 'a'] == k) {
                    usefulCount--;
                }
                countArr[arr[L] - 'a']--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "eeabcabcabceccfg";
        int k = 2;
        System.out.println(Problem_0395_LongestSubstringWithAtLeastKRepeatingCharacters.longestSubstring2(str, k));
    }

}

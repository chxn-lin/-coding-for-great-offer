package a.com.linshunc.class34;

import class34.Problem_0340_LongestSubstringWithAtMostKDistinctCharacters;

import java.util.HashMap;
import java.util.Map;

public class Prob_0340_LongestSubStr {

    public static void main(String[] args) {
        String str = "sdagahwqehqwwqwwewweerqqq";
        int k = 3;
        System.out.println(lengthOfLongestSubstringKDistinct(str, k));
        System.out.println(Problem_0340_LongestSubstringWithAtMostKDistinctCharacters.lengthOfLongestSubstringKDistinct(str, k));
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() < 1 || k < 1) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int L = 0;
        int R = 0;
        int ans = 0;
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (R < arr.length) {
            if (map.containsKey(arr[R])) {
                map.put(arr[R], map.get(arr[R]) + 1);
                R++;
                ans = Math.max(ans, R - L);
            }
            else {
                map.put(arr[R], 1);
                count++;
                while (count > k) {
                    map.put(arr[L], map.get(arr[L]) - 1);
                    if (map.get(arr[L]) - 1 == 0) {
                        count--;
                        map.remove(arr[L]);
                    }
                    L++;
                }
            }
        }
        return ans;
    }

}

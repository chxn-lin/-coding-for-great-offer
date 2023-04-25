package a.com.linshunc.class33;

public class Prob_0242_ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int[] arr = new int[256];
        for (char c : sArr) {
            arr[c]++;
        }
        for (char c : tArr) {
            if (--arr[c] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

}

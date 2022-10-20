package a.com.linshunc.class09;

import java.util.ArrayList;
import java.util.List;

import static class09.Code02_RemoveInvalidParentheses.removeInvalidParentheses;

public class Code02_RemoveLess {

    public static List<String> method1(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    private static void remove(String s, List<String> ans, int checkIndex, int removeIndex, char[] par) {
        for (int i = checkIndex, count = 0; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }
            if (count < 0) {
                for (int j = removeIndex; j <= i; j++) {
                    if (s.charAt(j) == par[1] && (j == removeIndex || s.charAt(j - 1) != par[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1),
                                ans, i, j, par);
                    }
                }
                return;
            }
        }
        String reverse = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(reverse, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(reverse);
        }
    }

    public static void main(String[] args) {
        String str = "(b(c)";
        System.out.println(removeInvalidParentheses(str));
        System.out.println(method1(str));
    }
}

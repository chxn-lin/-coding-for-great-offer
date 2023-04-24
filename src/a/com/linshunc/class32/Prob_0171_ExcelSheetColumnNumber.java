package a.com.linshunc.class32;

public class Prob_0171_ExcelSheetColumnNumber {

    public static int titleToNumber(String s) {
        if (s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int res = 0;
        int curMut = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            res += (chars[i] - 'A' + 1) * curMut;
            curMut *= 26;
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "FXSHRXW";
        System.out.println(titleToNumber(str));
    }

}

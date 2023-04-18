package a.com.linshunc.class28;

import class28.Problem_0012_IntegerToRoman;

public class Prob_0012_int2Roman {

    public static String intToRoman(int num) {
        String[][] c = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}
        };
        StringBuilder str = new StringBuilder();
        str.append(c[3][num / 1000])
                .append(c[2][num % 1000 / 100])
                .append(c[1][num % 100 / 10])
                .append(c[0][num % 10]);

        return str.toString();
    }

    public static void main(String[] args) {
        int times = 100_0000;
        for (int i = 0; i < times; i++) {
            int cur = random();
            String s = intToRoman(cur);
            String s2 = Problem_0012_IntegerToRoman.intToRoman(cur);
            if (!s.equals(s2)) {
                System.out.println("Oops");
                System.out.println(cur);
                System.out.println(s);
                System.out.println(s2);
                break;
            }
        }
    }

    public static int random(){
        return (int) (Math.random() * 3999) + 1;
    }
}

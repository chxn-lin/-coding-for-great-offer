package a.com.linshunc.class28;

import java.util.ArrayList;
import java.util.List;

public class Prob_0022_GenAllPath {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        process(list, 0, new char[n * 2], n, 0);
        return list;
    }

    public static void process(List<String> list, int index, char[] path, int leftRest, int leftMoreRight){
        if (index == path.length) {
            list.add(new String(path));
        }
        else {
            if (leftMoreRight == 0) {
                path[index] = '(';
                process(list, index + 1, path, leftRest - 1, leftMoreRight + 1);
            }
            else {
                if (leftRest > 0) {
                    path[index] = '(';
                    process(list, index + 1, path, leftRest - 1, leftMoreRight + 1);
                }
                path[index] = ')';
                process(list, index + 1, path, leftRest, leftMoreRight - 1);
            }
        }
    }

}
